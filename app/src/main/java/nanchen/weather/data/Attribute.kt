package nanchen.weather.data

/**
 * //在Kotlin中，如果我们想使用不可变性，我们编码时思考的方式需要有一些改变。
 * 一个重要的概念是：尽可能地使用val。
 * 除了个别情况（特别是在Android中，有很多类我们是不会去直接调用构造函数的），大多数时候是可以的。
 */
val i: Int = 7
val d: Double = i.toDouble()

val c: Char = 'c'
val i2: Int = c.toInt()

val bitwiseOr = 0x0001 or 0x0010
val bitwiseAnd = 0x0001 and 0x0010

val i3 = 12
val iHex = 0x0f
val l = 3L
val d2 = 3.5
val f = 3.5F

val s = "Example"
val c2 = s[2]

fun iterate(s: String) {
    for (c in s) {
        print(c)
    }
}
