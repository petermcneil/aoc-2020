package mcneil.aoc.twentytwenty

import org.scalatest.FunSuite

import scala.io.Source
import scala.util.{Failure, Success, Try}

trait Testing extends FunSuite {
  protected def loadPathToString(path: String): String = {
    readSourceToString(Source.fromResource(path.stripPrefix("/"))) match {
      case Failure(exception) => throw exception
      case Success(value) => value
    }
  }

  protected def loadPathToSeq(path: String): Seq[String] = {
    readSourceToSeq(Source.fromResource(path.stripPrefix("/"))) match {
      case Failure(exception) => throw exception
      case Success(value) => value
    }
  }


  private def readSourceToString(source: Source): Try[String] = Try(try source.mkString finally source.close())
  private def readSourceToSeq(source: Source): Try[Seq[String]] = Try(try source.getLines().toSeq finally source.close())
}
