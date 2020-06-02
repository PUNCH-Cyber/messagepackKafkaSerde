/*
 * Copyright (c) 2020 Punch Cyber Analytics Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.punchcyber.patternicity.serde.msgpack

import java.io.IOException
import java.util

import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Deserializer
import org.msgpack.core.{MessagePack, MessageUnpacker}

@SerialVersionUID(10101010L)
class msgpackDeserializer extends Deserializer[Array[Byte]] {
    override def deserialize(topic: String, data: Array[Byte]): Array[Byte] = {
        try {
            if(data == null) {
                null
            }
            else {
                val unpacker: MessageUnpacker = MessagePack.newDefaultUnpacker(data)
                val outString: String = unpacker.unpackValue().toJson
                unpacker.close()
                outString.getBytes
            }
        } catch {
            case e: IOException =>
                throw new SerializationException("Error while deserializing MessagePack format Byte array: " + e.getStackTrace.mkString("\n"))
        }
    }
    
    override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}
    
    override def close(): Unit = {}
}
