/*
 * Copyright (c) 2020 Punch Cyber Analytics Group
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.punchcyber.kafka_serde.msgpack

import java.io.IOException
import java.util

import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Serializer
import org.msgpack.core.{MessageBufferPacker, MessagePack}

@SerialVersionUID(10101010L)
class msgpackSerializer extends Serializer[String] {
  override def serialize(topic: String, data: String): Array[Byte] = {
    try {
      if (data == null) {
        null
      } else {
        val packer: MessageBufferPacker = MessagePack.newDefaultBufferPacker()
        packer.packString(data)
        val outBytes: Array[Byte] = packer.toByteArray
        packer.close()
        outBytes
      }
    } catch {
      case e: IOException =>
        throw new SerializationException(
          "Error serializing string as Byte array: " + e.getStackTrace
            .mkString("\n")
        )
    }
  }

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {
    super.configure(configs, isKey)
  }

  override def close(): Unit = {
    super.close()
  }
}
