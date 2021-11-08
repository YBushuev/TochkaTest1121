package io.bushuev

import scala.annotation.tailrec

class TreeNode[X](val value: X, val left: Option[TreeNode[X]], val right: Option[TreeNode[X]]) {}

object TreeNode {

  def apply[X](value: X, left: Option[TreeNode[X]], right: Option[TreeNode[X]]) = new TreeNode[X](value, left, right)

  def isSameTreeF[X](p: TreeNode[X], q: TreeNode[X]): Boolean = isSameTree(Some(p), Some(q))

  def isSameTree[X](p: Option[TreeNode[X]], q: Option[TreeNode[X]]): Boolean = {

    @tailrec
    def loop(stack: List[(Option[TreeNode[X]], Option[TreeNode[X]])], state: Boolean): Boolean =
      stack match {
        case Nil                                              => state
        case (None, None) :: tail                             => loop(tail, state = true)
        case (None, Some(_)) :: _                             => false
        case (Some(_), None) :: _                             => false
        case (Some(l), Some(r)) :: tail if l.value == r.value =>
          loop((l.left, r.left) :: (l.right, r.right) :: tail, l.value == r.value)
        case _                                                => false
      }

    loop(List(p -> q), state = false)
  }

}
