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
