package jp.xet.sample.brainfuck

/**
 * Created by IntelliJ IDEA.
 * User: daisuke
 * Date: 11/02/25
 * Time: 20:48
 * To change this template use File | Settings | File Templates.
 */

sealed abstract class Command {
  val env:Environment
  def execute:Unit
}

case class InputMemoryAtPointer(env: Environment) extends Command {
  def execute = env.memory(env.pointer) = Console.in.read
}

case class OutputMemoryAtPointer(env: Environment) extends Command {
  def execute = print(env.memoryAtPointer.toChar)
}

case class IncrementPointer(env: Environment) extends Command {
  def execute = env.pointer += 1
}

case class DecrementPointer(env: Environment) extends Command {
  def execute = env.pointer -= 1
}

case class IncrementMemoryAtPointer(env: Environment) extends Command {
  def execute = env.memory(env.pointer) += 1
}

case class DecrementMemoryAtPointer(env: Environment) extends Command {
  def execute = env.memory(env.pointer) -= 1
}

case class OpenLoop(env: Environment) extends Command {
  def execute = {
    if (env.memoryAtPointer == 0) {
      var depth = 0
      while (env.programChar != ']' || depth != 1) {
        if (env.programChar == '[') {
          depth += 1
        } else if (env.programChar == ']') {
          depth -= 1
        }
        env.counter += 1
      }
    }
  }
}
case class CloseLoop(env:Environment) extends Command {
  def execute = {
    if (env.memoryAtPointer != 0) {
      var depth = 0
      while (env.programChar != '[' || depth != 1) {
        if (env.programChar == ']') {
          depth += 1
        } else if (env.programChar == '[') {
          depth -= 1
        }
        env.counter -= 1
      }
    }
  }
}
