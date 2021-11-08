import io.bushuev.StringSorter
import munit.ScalaCheckSuite
import org.scalacheck.Gen
import org.scalacheck.Prop._

import scala.util.Random

class StringSorterSpecs extends ScalaCheckSuite {

  import StringSorter.ops._

  test("should sort example string") {

    val testString = "asdasdaaaweqbbbbasdasd"

    // группы с одинаковым кол-вом символов могут быть в произвольном порядке, например “qwe” или “eqw”
    assertEquals(testString.sortByCharCount, "aaaaaaabbbbddddsssseqw")
  }

  property("should sort generated string") {
    forAll(strGen) { str =>
      val permuted = Random.shuffle(str).mkString("")
      assertEquals(permuted.sortByCharCount, str)
    }
  }

  def tupleGen: Gen[(Char, Int)] = for {
    k <- Gen.asciiPrintableChar
    v <- Gen.choose(0, 10000)
  } yield (k, v)

  def strGen: Gen[String] = for {
    map <- Gen.mapOf(tupleGen)
  } yield map.toList.sortBy(x => (-x._2, x._1)).map { case (c, n) => c.toString * n }.mkString("")

}
