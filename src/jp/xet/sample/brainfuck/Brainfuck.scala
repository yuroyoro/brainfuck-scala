package jp.xet.sample.brainfuck

/**
 * Created by IntelliJ IDEA.
* User: daisuke
 * Date: 11/02/25
 * Time: 11:38
 * To change this template use File | Settings | File Templates.
 */

object Brainfuck {

  def main(args:Array[String]) {
    val program = readSource
    for(cmd <- new Environment(program)){
      cmd.execute
    }
  }

  def readSource = {
    // output "Hello, world!"
    """
+++++++++[>++++++++>+++++++++++>+++++<<<-]>.>++.+++++++..+++.>-.
------------.<++++++++.--------.+++.------.--------.>+.
    """

    // output "hoge"
//    """
//++++++++++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++
//++++++++.
//+++++++.
//--------.
//--.
//    """
  }
}
