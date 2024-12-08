import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.abs

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readText().trim().lines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun <T, E>checkEquals(expected: T, actual: E) {
    if (expected != actual) {
        val message = "Check failed. Expected $expected, got $actual"
        throw IllegalStateException(message)
    }
}

fun <T>Any?.checkEqualTo(expected: T) = checkEquals(expected = expected, actual = this)

fun <T> List<T>.toPair(): Pair<T, T> {
    require(size == 2)
    return Pair(this[0], this[1])
}

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

fun gcd(a: Int, b: Int): Int {
    return if (b == 0) a else gcd(b, a % b)
}

fun lcm(a: Int, b: Int): Int {
    return abs(a * b) / gcd(a, b)
}