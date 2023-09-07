import arrow.core.Option
import arrow.core.continuations.ensureNotNull
import arrow.core.continuations.nullable
import arrow.core.continuations.option
import arrow.core.none
import arrow.core.toOption

object ErrorHandling {

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
                Job(JobId(1), Company("Apple"), Role("Data Engineer"), Salary(100_000.00)),
        JobId(2) to
                Job(JobId(2), Company("Microsoft"), Role("Software Engineer"), Salary(100_001.00)),
        JobId(3) to
                Job(JobId(3), Company("Google"), Role("Site Reliability Engineer"), Salary(100_002.00)),
    )

    interface Jobs {
        fun findById(id: JobId): Job?
        fun findByIOpt(id: JobId): Option<Job>
        fun findAll(): List<Job>
    }

    // throw an exception if the input is invalid

    /*
        class NaiveJobs : Jobs {
            override fun findById(id: JobId): Job {
                val maybeJob: Job? = JOB_DATABASE[id]
                if (maybeJob != null) {
                    return maybeJob
                } else {
                    throw NoSuchElementException("Job not found")
                }
            }
        }
    */

    class LiveJobs : Jobs {
        override fun findById(id: JobId): Job? = try {
            JOB_DATABASE[id]
        } catch (e: Exception) {
            null
        }

        override fun findByIOpt(id: JobId): Option<Job> = try {
            JOB_DATABASE[id].toOption()
        } catch (e: Exception) {
            none()
        }

        override fun findAll(): List<Job> =
            JOB_DATABASE.values.toList()
    }

    class JobsService(private val jobs: Jobs, private val converter: CurrencyConverter) {
        fun retrieveSalary(id: JobId): Double =
            jobs.findById(id)?.salary?.value ?: 0.0

        fun retrieveSalaryEur(id: JobId): Double =
            jobs.findById(id)?.let { converter.usd2Eur(it.salary.value) } ?: 0.0

        // Option(jobs.findById(id)).map(job => converter.usd2Eur(job.salary.value)).getOrElse(0.0)

        fun isFromCompany(id: JobId, company: String): Boolean =
            jobs.findById(id)?.takeIf {
                it.company.name == company
            } != null

        // This is not readable
        fun sumSalaries(jobId1: JobId, jobId2: JobId): Double? {
            val maybeJob1 = jobs.findById(jobId1)
            val maybeJob2 = jobs.findById(jobId2)
            return maybeJob1?.let { job1 ->
                maybeJob2?.let { job2 ->
                    job1.salary.value + job2.salary.value
                }
            }
        }

        // Arrow library
        fun sumSalariesArrow(jobId1: JobId, jobId2: JobId): Double? = nullable.eager {
            println("Searching for job $jobId1")
            val job1: Job = jobs.findById(jobId1).bind()
            println("Job 1 found: $job1")
            println("Searching for job $jobId2")
            val job2: Job = ensureNotNull(jobs.findById(jobId2)) // ensure first it's not null before to search other
            println("Job 2 found: $job2")
            job1.salary.value + job2.salary.value
        }

        fun salaryGapVsMax(jobId: JobId): Option<Double> {
            val maybeJob: Option<Job> = jobs.findByIOpt(jobId)
            val maybeMaxSalary = jobs.findAll().maxBy { it.salary.value }.toOption()
            return maybeJob.flatMap { job ->
                maybeMaxSalary.map { maxSalary ->
                    maxSalary.salary.value - job.salary.value
                }
            }
        }

        fun salaryGapVsMaxV2(jobId: JobId): Option<Double> = option.eager {
            println("Searching for job id $jobId")
            val job: Job = jobs.findByIOpt(jobId).bind()
            println("Job found $job")
            println("Searching max salary")
            val maxSalary = jobs.findAll().maxBy { it.salary.value }.toOption().bind()
            maxSalary.salary.value - job.salary.value
        }
    }

    // Referential Transparency -> returns always the same value if invoked with same parameters
    // An expression can be replaced with its value without changing the program’s behavior
    // A function witch returns exception it's NOT a referential transparent function
    // Checked exceptions don't work well with FP (HOFs)

    // Nullable values -> let
    class CurrencyConverter {
        fun usd2Eur(amount: Double): Double = amount * 0.91
    }

    // Options

    @JvmStatic
    fun main(args: Array<String>) {
        val jobs = LiveJobs()
        val currencyConverter = CurrencyConverter()
        val jobsService = JobsService(jobs, currencyConverter)
        val jobId: Long = 42
        val salary = jobsService.retrieveSalary(JobId(jobId))

        val otherJobId: Long = 1
        val companyName = "Apple"
        val isAppleCompany = jobsService.isFromCompany(JobId(otherJobId), companyName)

        val sumSalaries = jobsService.sumSalariesArrow(JobId(1), JobId(42)) ?: 0.0

        val gapWithMaxSalaryV1 = jobsService.salaryGapVsMax(JobId(1))
        val gapWithMaxSalaryV2 = jobsService.salaryGapVsMaxV2(JobId(1))

        //println("Salary for the job $jobId is $salary")
        //println("Job id $otherJobId is for Apple $isAppleCompany")
        //println("Sum of salaries of jobs:  $sumSalaries")
        println("Gap with max salary V1:  $gapWithMaxSalaryV1")
        println("Gap with max salary V2:  $gapWithMaxSalaryV2")
    }

}