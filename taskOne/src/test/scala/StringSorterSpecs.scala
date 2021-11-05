import io.bushuev.StringSorter
import munit.ScalaCheckSuite
import org.scalacheck.Gen
import org.scalacheck.Prop._

import scala.util.Random

class StringSorterSpecs extends ScalaCheckSuite {

  test("should sort example string") {

    val testString = "asdasdaaaweqbbbbasdasd"

    // группы с одинаковым кол-вом символов могут быть в произвольном порядке, например “qwe” или “eqw”
    assertEquals(StringSorter.sortByCharCount(testString), "aaaaaaabbbbddddsssseqw")
  }

  property("should sort generated string") {
    forAll(strGen) { str =>
      val permuted = Random.shuffle(str).mkString("")
      assertEquals(StringSorter.sortByCharCount(permuted), str)
    }
  }

  def strGen: Gen[String] = for {
    map <- Gen.mapOf(Gen.asciiPrintableChar.flatMap(c => Gen.choose(0, 10000).map(c -> _)))
  } yield map.toList.sortBy(x => (-x._2, x._1)).map { case (c, n) => c.toString * n }.mkString("")

}
