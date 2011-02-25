package jp.xet.sample.brainfuck

/**
 * Created by IntelliJ IDEA.
 * User: daisuke
 * Date: 11/02/25
 * Time: 20:50
 * To change this template use File | Settings | File Templates.
 */

class Environment(val program: String) extends Iterator[Command] {
  // from scala.collection.Iterator
  def hasNext: Boolean = isEof != true
  def next():Command =
    if( isEof ) {
      throw new java.util.NoSuchElementException("no more program charctors.")
    }else {
      val cmd = command
      progress
      cmd
    }

  val memory = new Array[Int](3000)
  var pointer = 0
  var counter = 0

  val commands:Map[Char, Environment => Command] = Map(
    '>' -> {(env:Environment) => new IncrementPointer(env)},
    '<' -> {(env:Environment) => new DecrementPointer(env)},
    '+' -> {(env:Environment) => new IncrementMemoryAtPointer(env)},
    '-' -> {(env:Environment) => new DecrementMemoryAtPointer(env)},
    '.' -> {(env:Environment) => new OutputMemoryAtPointer(env)},
    '[' -> {(env:Environment) => new OpenLoop(env)},
    ']' -> {(env:Environment) => new CloseLoop(env)}
  )

  def command:Command = commands.get(programChar).map{ f => f(this) }.getOrElse( new NoOp(this) )

  def isEof = counter >= program.length
  def programChar = program(counter)
  def progress:Unit = counter += 1
  def memoryAtPointer = memory(pointer)

}
