import arrow.core.Either
import arrow.core.continuations.either
import arrow.core.continuations.ensureNotNull
import arrow.core.continuations.result
import arrow.core.flatMap
import arrow.core.left
import arrow.core.right

/*
    Result is a wrapper for potentially FAILED computations
    Provides a way to work with failing more readable and easy to understand than try/catch
    Also gives the opportunity to recover from exceptions

    Either it's similar but also allows us to represent errors as any kind of type we find useful, besides the JVM Throwable type.
*/
object ErrorHandlingPart2 {

    data class Job(val id: JobId, val company: Company, val role: Role, val salary: Salary)

    @JvmInline
    value class JobId(val value: Long)

    @JvmInline
    value class Company(val name: String)

    @JvmInline
    value class Role(val name: String)

    @JvmInline
    value class Salary(val value: Double) {
        operator fun compareTo(other: Salary): Int = value.compareTo(other.value)
    }

    // "Database"
    val JOB_DATABASE: Map<JobId, Job> = mapOf(
        JobId(1) to
                Job(JobId(1), Company("Apple"), Role("Software Engineer"), Salary(100_000.00)),
        JobId(2) to
                Job(JobId(2), Company("Microsoft"), Role("Software Engineer"), Salary(100_001.00)),
        JobId(3) to
                Job(JobId(3), Company("Google"), Role("SRE"), Salary(100_002.00)),
    )


    interface Jobs {
        fun findById(id: JobId): Result<Job?>
        fun findByIdE(id: JobId): Either<JobError, Job>
        fun findAll(): Result<List<Job>>
        fun findAllE(): Either<JobError, List<Job>>
    }


    val appleJobResult: Result<Job> = Result.success(
        Job(JobId(1), Company("Apple"), Role("Software Engineer"), Salary(100_000.00))
    )

    val notFoundJob: Result<Job> = Result.failure(NoSuchElementException("Job not found"))


    private fun <T> T.toResult(): Result<T> = if (this is Throwable)
        Result.failure(this)
    else
        Result.success(this)

    val aResult = Job(
        JobId(1),
        Company("Apple"),
        Role("Software Engineer"),
        Salary(100_000.00)
    ).toResult()

    class LiveJobs : Jobs {
        fun findById1(id: JobId): Result<Job?> = try {
            Result.success(JOB_DATABASE[id])
        } catch (e: Exception) {
            Result.failure(e)
        }

        override fun findById(id: JobId): Result<Job?> = runCatching {
            JOB_DATABASE[id]
        }

        fun findByIdE1(id: JobId): Either<JobError, Job> = try {
            JOB_DATABASE[id]?.right() ?: JobNotFound(id).left()
        } catch (e: Exception) {
            GenericError(e.message ?: "Unknown error").left()
        }

        override fun findByIdE(id: JobId): Either<JobError, Job> = Either.catch {
            JOB_DATABASE[id]
        }
            .mapLeft { GenericError(it.message ?: "Unknown error") }
            .flatMap { maybeJob -> maybeJob?.right() ?: JobNotFound(id).left() }

        override fun findAll(): Result<List<Job>> = Result.success(JOB_DATABASE.values.toList())
        override fun findAllE(): Either<JobError, List<Job>> = JOB_DATABASE.values.toList().right()
    }

    class CurrencyConverter {
        fun convertUsd2Eur(amount: Double?): Double =
            if (amount != null && amount >= 0.0)
                amount * 0.91
            else
                throw IllegalArgumentException("Amount must be present and positive")
    }

    class JobService(private val jobs: Jobs, private val currencyConverter: CurrencyConverter) {
        fun maybePrintJob(jobId: JobId) {
            val maybeJob: Result<Job?> = jobs.findById(jobId)
            if (maybeJob.isSuccess) {
                maybeJob.getOrNull()?.apply { println("Job found $this") } ?: println("Job not found")
            } else {
                println("Something went wrong: ${maybeJob.exceptionOrNull()}")
            }
        }

        fun getSalaryInEur(jobId: JobId): Result<Double?> =
            jobs.findById(jobId)
                .map { it?.salary }
                .mapCatching { currencyConverter.convertUsd2Eur(it?.value) }

        private fun List<Job>.maxSalary(): Result<Salary> = runCatching {
            if (this.isEmpty())
                throw NoSuchElementException("No jobs present")
            else
                this.maxBy { it.salary.value }.salary
        }

        private fun List<Job>.maxSalaryE(): Either<JobError, Salary> =
            if (this.isEmpty())
                GenericError("No jobs present").left()
            else
                this.maxBy { it.salary.value }.salary.right()

        fun getSalaryGapVsMaxNonIdiomatic(jobId: JobId): Result<Double> = runCatching {
            // non-idiomatic manually manipulated
            val maybeJob = jobs.findById(jobId).getOrThrow()
            val salaryJob = maybeJob?.salary ?: Salary(0.0)

            val jobList = jobs.findAll().getOrThrow()
            val maxSalary = jobList.maxSalary().getOrThrow()

            maxSalary.value - salaryJob.value
        }

        // no need to use runCatching
        // flatmap comes from arrow library
        fun getSalaryGapVsMax(jobId: JobId): Result<Double> =
            jobs.findById(jobId).flatMap { maybeJob ->
                val salary = maybeJob?.salary ?: Salary(0.0)
                jobs.findAll().flatMap { jobsList ->
                    jobsList.maxSalary().map { maxSalary ->
                        maxSalary.value - salary.value
                    }
                }
            }

        fun getSalaryGapVsMaxArrow(jobId: JobId): Result<Double> = result.eager {
            val maybeJob: Job? = jobs.findById(jobId).bind() // with bind breaks the chain if some exception
            ensureNotNull(maybeJob) { NoSuchElementException("Job not found") }
            val jobSalary = maybeJob.salary
            val jobList = jobs.findAll().bind()
            val maxSalary = jobList.maxSalary().bind()
            maxSalary.value - jobSalary.value
        }

        fun getSalaryVsMaxArrowEither(jobId: JobId): Either<JobError, Double> =
            jobs.findByIdE(jobId).flatMap { job ->
                jobs.findAllE().flatMap { jobsList ->
                    jobsList.maxSalaryE().map { maxSalary ->
                        maxSalary.value - job.salary.value
                    }
                }
            }

        fun getSalaryVsMaxArrowEitherV2(jobId: JobId): Either<JobError, Double> = either.eager {
            val maybeJob: Job = jobs.findByIdE(jobId).bind()
            val jobSalary = maybeJob.salary
            val jobList = jobs.findAllE().bind()
            val maxSalary = jobList.maxSalaryE().bind()
            maxSalary.value - jobSalary.value
        }

    }

    sealed interface JobError
    data class JobNotFound(val jobId: JobId) : JobError
    data class GenericError(val cause: String) : JobError

    /*
        Either type
    */

    // Either from Arrow
    val appleJobEither: Either<JobError, Job> = Either.Right(JOB_DATABASE[JobId(1)]!!)
    val appleJobEither_v2: Either<JobError, Job> = JOB_DATABASE[JobId(1)]!!.right()


    @JvmStatic
    fun main(args: Array<String>) {
        val jobs = LiveJobs()
        val currencyConverter = CurrencyConverter()
        val jobService = JobService(jobs, currencyConverter)

        // jobService.maybePrintJob(JobId(6))
        println(jobService.getSalaryInEur(JobId(6)))
        println(jobService.getSalaryInEur(JobId(6)).getOrDefault(0.0))
        // Recovering
        println(jobService.getSalaryInEur(JobId(6)).recover {
            when (it) {
                is java.lang.IllegalArgumentException -> println("From recover: Amount must be positive")
                else -> println("Some other error ${it.message}")
            }

            0.0
        })
        println(jobService.getSalaryInEur(JobId(6)).fold(
            {
                "The salary for the job is: $it"
            }, {
                when (it) {
                    is java.lang.IllegalArgumentException -> println("From recover: Amount must be positive")
                    else -> println("Some other error ${it.message}")
                }
                "Job not found"
            }
        )
        )

        println(jobService.getSalaryInEur(JobId(1)).getOrDefault(0.0))

    }

}