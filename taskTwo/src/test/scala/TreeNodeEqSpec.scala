import io.bushuev.TreeNode
import munit._
import org.scalacheck.Gen
import org.scalacheck.Prop._

class TreeNodeEqSpec extends ScalaCheckSuite {

  test("example test") {

    val simpleP = TreeNode(1, None, None)
    val simpleQ = TreeNode(1, None, None)

    assertEquals(TreeNode.isSameTree(Some(simpleP), Some(simpleQ)), true)
    val p = TreeNode(1, Some(TreeNode(2, None, None)), None)
    val q = TreeNode(1, None, Some(TreeNode(2, None, None)))
    assertEquals(TreeNode.isSameTree(Some(p), Some(q)), false)
    assertEquals(TreeNode.isSameTree(Some(p), None), false)

  }

  test("should compare nulls") {

    val simpleP: TreeNode[Any] = TreeNode(null, None, None)
    val simpleQ: TreeNode[Any] = TreeNode(null, None, None)
    val a: TreeNode[Any]       = TreeNode(1, Some(simpleP), Some(simpleQ))
    val b: TreeNode[Any]       = TreeNode(null, Some(simpleP), Some(simpleQ))

    assertEquals(TreeNode.isSameTree(Some(simpleP), Some(simpleQ)), true)
    assertEquals(TreeNode.isSameTreeF(simpleP, a), false)
    assertEquals(TreeNode.isSameTreeF(b, a), false)

  }

  test("should compare Int") {

    val a: TreeNode[Int] = TreeNode[Int](10, None, None)
    val b                = TreeNode[Int](10, None, None)
    val c                = TreeNode[Int](2, None, None)

    assertEquals(TreeNode.isSameTreeF(a, b), true)
    assertEquals(a.equals(b), false)
    assertEquals(TreeNode.isSameTreeF(b, c), false)

  }

  test("should work for AnyRef") {

    val listA: AnyRef = List(1, 2, 3)
    val listB: AnyRef = Nil

    val a = TreeNode[AnyRef](listA, None, None)
    val b = TreeNode[AnyRef](listA, None, None)
    val c = TreeNode[AnyRef](listB, None, None)

    assertEquals(TreeNode.isSameTreeF(a, b), true)
    assertNotEquals(TreeNode.isSameTreeF(a, c), true)

  }

  property("should work for int gen values") {

    val intGen: Gen[Int] = Gen.choose(1, 2)

    forAll(genTree[Int](intGen)) { x =>
      val a = x
      val b = TreeNode[Int](a.value, a.left, a.right)
      val c = TreeNode[Int](10, a.left, a.right)

      assertEquals(TreeNode.isSameTreeF(a, b), true)
      assertEquals(TreeNode.isSameTreeF(a, c), false)
    }

  }

  property("should work for map gen values") {

    val tupleGen = for {
      a <- Gen.long
      b <- Gen.long
    } yield (a, b)

    val mapGen: Gen[Map[Long, Long]] = Gen.mapOf(tupleGen)

    forAll(genTree[Map[Long, Long]](mapGen)) { x =>
      val b = TreeNode[Map[Long, Long]](x.value, x.left, x.right)

      assertEquals(TreeNode.isSameTreeF(x, b), true)

    }

  }

  property("should work for class gen values") {

    val fooGen: Gen[Foo] = for {
      a <- Gen.long
    } yield Foo(a)

    forAll(genTree[Foo](fooGen)) { x =>
      val b = TreeNode[Foo](x.value, x.left, x.right)
      val c = TreeNode[Foo](new Foo(1L), x.left, x.right)

      assertEquals(TreeNode.isSameTreeF(x, b), true)
      assertNotEquals(TreeNode.isSameTreeF(x, c), true)

    }

  }

  def genLeaf[X](g: Gen[X]): Gen[TreeNode[X]] = g.map(TreeNode[X](_, None, None))

  def genNode[X](g: Gen[X]): Gen[TreeNode[X]] = for {
    v     <- g
    left  <- Gen.sized(h => Gen.resize(h / 2, genTree[X](g)))
    right <- Gen.sized(h => Gen.resize(h / 2, genTree[X](g)))
  } yield TreeNode(v, Some(left), Some(right))

  def genTree[X](g: Gen[X]): Gen[TreeNode[X]] = Gen.sized { height =>
    if (height <= 0) {
      genLeaf[X](g)
    } else {
      Gen.oneOf(genLeaf[X](g), genNode[X](g))
    }
  }
}

case class Foo(value: Long) extends AnyVal {

  def foo: Long = value

  override def toString: String = s"FOO: $value"

}
