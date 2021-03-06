package io.bushuev

object StringSorter {

  def sortByCharCount(str: String): String =
    str
      .groupBy(identity)
      .toSeq
      .sortBy { case (c, s) =>
        (-s.length, c)
      }
      .map(_._2)
      .mkString("")

  object ops {

    implicit class StringOps(value: String) {

      def sortByCharCount: String = StringSorter.sortByCharCount(value)

    }
  }

}
