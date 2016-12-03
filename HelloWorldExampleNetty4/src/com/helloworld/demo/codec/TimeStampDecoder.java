package com.helloworld.demo.codec ;

/**
 * Turns a byte array into a LoopBackTimeStamp object.
 */

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
 
import java.util.List;
 
import com.helloworld.demo.LoopBackTimeStamp;
 
// Extends ByteToMessageDecoder because that is what we are doing:
// Decoding a byte(array) to a Message (LoopBackTimeStamp object).
public class TimeStampDecoder extends ByteToMessageDecoder {
 
  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    
      //since we expect two Longs, messageLength is Long.SIZE/Byte.SIZE *2
      //if we were expecting n objects called Object, then:
      //final int messageLength = Object.SIZE/Byte.SIZE * n.
      final int messageLength = Long.SIZE/Byte.SIZE *2;
      
      //can't make a message (LoopBackTimeStampObject) with correct size,
      //so return.
      //TODO: Try to make it so it pads the byteArray in case the bytes received
      //are not sufficient
      if (in.readableBytes() < messageLength) {
          return;    
      }
      
      byte [] ba = new byte[messageLength];
      in.readBytes(ba, 0, messageLength);  // block until read 16 bytes from sockets
      LoopBackTimeStamp loopBackTimeStamp = new LoopBackTimeStamp();
      loopBackTimeStamp.fromByteArray(ba);
      out.add(loopBackTimeStamp);
  }
}