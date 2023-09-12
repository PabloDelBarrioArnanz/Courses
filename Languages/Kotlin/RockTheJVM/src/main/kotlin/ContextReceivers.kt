import ContextReceivers.JOB_DATABASE
import ContextReceivers.consoleLogger
import ContextReceivers.jobJsonScope
import ContextReceivers.printAsJson

// Context receivers provide a convenient way to access functions and properties of multiple receivers within a specific scope
object ContextReceivers {

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
                Job(JobId(1), Company("Apple, Inc."), Role("Software Engineer"), Salary(70_000.00)),
        JobId(2) to
                Job(JobId(2), Company("Microsoft"), Role("Software Engineer"), Salary(80_001.00)),
        JobId(3) to
                Job(JobId(3), Company("Google"), Role("Software Engineer"), Salary(90_002.00)),
    )

    fun Job.toJson(): String =
        """
               {
                "id": ${id.value}
                "company": ${company.name}
                "role": ${role.name}
                "salary": ${salary.value}
               }
        """.trimIndent()

    // Scope
    // In a normal extension function we have a receiver and the function
    // Here we have a dispatcher (interface) and the generic it's the receiver
    // Then a scope it's a safe place where call this function
    interface JsonScope<A> {
        fun A.toJson(): String
    }

    // Now we can extend a function in the dispatcher that way we can invoke our function
    /*
    fun <A> JsonScope<A>.printAsJson(things: List<A>): String =
        things.joinToString(separator = ", ", prefix = "[", postfix = "]") {
            it.toJson()
        }


    val jobJsonScope = object : JsonScope<Job> {
        override fun Job.toJson(): String =
            """
               {
                "id": ${id.value}
                "company": ${company.name}
                "role": ${role.name}
                "salary": ${salary.value}
               }
            """.trimIndent()
    }

    fun main() {
        with(jobJsonScope) {
            println(printAsJson(JOB_DATABASE.values.toList()))
        }
    }
    */

    // coroutines - launch, async uses this mechanism

    // Limitations
    // 1- desired function is an extension of the scope itself jobJsonScope.printAsJson
    // 2- desired function has no connection to the type itself
    // 3- one scope at one time

    // To avoid those limitations we have context receivers

    val jobJsonScope = object : JsonScope<Job> {
        override fun Job.toJson(): String =
            """
               {
                "id": ${id.value}
                "company": ${company.name}
                "role": ${role.name}
                "salary": ${salary.value}
               }
            """.trimIndent()
    }

    context (JsonScope<A>, Logger)
    fun <A> printAsJson(things: List<A>): String {
        info("Serializing $things as Json")
        return things.joinToString(separator = ", ", prefix = "[", postfix = "]") {
            it.toJson()
        }
    }

    interface Logger {
        fun info(message: String)
    }

    val consoleLogger = object : Logger {
        override fun info(message: String) {
            println("[INFO] $message")
        }
    }

    // Other example
    interface Jobs {
        fun findById(id: JobId): Job?
    }

    context (Logger)
    class LiveJobs : Jobs {
        override fun findById(id: JobId): Job? {
            info("Searching for job")
            return JOB_DATABASE[id]
        }
    }

    //context (JsonScope<Job>, Logger, Jobs) the idea it's not to use context for business dependencies and pass it explicitly
    context (JsonScope<Job>, Logger) // Context for abilities
    class JobController(private val jobs: Jobs) { // read dependencies injection
        fun jsonById(id: String): String {
            // this@Logger.info("Searching for $id to serialize")
            info("Searching for $id to serialize")
            // return findById(JobId(id.toLong()))?.let {
            return jobs.findById(JobId(id.toLong()))?.let {
                info("Job with id $id found")
                return it.toJson()
            } ?: "No job found"
        }
    }

}


// Context receivers gran functionality to some types not others
fun main() {
    with(jobJsonScope) {
        with(consoleLogger) {
            println(printAsJson(JOB_DATABASE.values.toList()))
        }
    }
}