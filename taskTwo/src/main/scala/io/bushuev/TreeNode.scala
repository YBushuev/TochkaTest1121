package io.bushuev

import scala.annotation.tailrec

class TreeNode[X](val value: X, val left: Option[TreeNode[X]], val right: Option[TreeNode[X]]) {

  override def equals(that: Any): Boolean = that match {
    case that: TreeNode[_] if that.isInstanceOf[TreeNode[_]] =>
      value == that.value &&
        left == that.left &&
        right == that.right
    case _                                                   => super.equals(that)
  }

}

object TreeNode {

  def apply[X](value: X, left: Option[TreeNode[X]], right: Option[TreeNode[X]]) = new TreeNode[X](value, left, right)

  def isSameTree[X](p: Option[TreeNode[X]], q: Option[TreeNode[X]]): Boolean = {

    @tailrec
    def loop(stack: List[(Option[TreeNode[X]], Option[TreeNode[X]])], state: Boolean): Boolean =
      stack match {
        case Nil                        => state
        case (None, None) :: tail       => loop(tail, state = true)
        case (None, Some(_)) :: _       => loop(Nil, state = false)
        case (Some(_), None) :: _       => loop(Nil, state = false)
        case (Some(l), Some(r)) :: tail =>
          loop((l.left, r.left) :: (l.right, r.right) :: tail, l.value == r.value)
      }

    loop(List(p -> q), state = true)
  }

}
