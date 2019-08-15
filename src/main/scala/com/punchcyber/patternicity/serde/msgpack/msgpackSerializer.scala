package com.punchcyber.patternicity.serde.msgpack

import java.io.IOException
import java.util

import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Serializer
import org.msgpack.core.{MessageBufferPacker, MessagePack}

@SerialVersionUID(10101010L)
class msgpackSerializer extends Serializer[String] {
    override def serialize(topic: String, data: String): Array[Byte] = {
        try {
            if(data == null) {
                null
            }
            else {
                val packer: MessageBufferPacker = MessagePack.newDefaultBufferPacker()
                packer.packString(data)
                val outBytes: Array[Byte] = packer.toByteArray
                packer.close()
                outBytes
            }
        } catch {
            case e: IOException =>
                throw new SerializationException("Error serializing string as Byte array: " + e.getStackTrace.mkString("\n"))
        }
    }
    
    override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}
    
    override def close(): Unit = {}
}
