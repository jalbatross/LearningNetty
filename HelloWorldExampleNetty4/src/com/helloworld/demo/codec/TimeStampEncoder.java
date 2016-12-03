package com.helloworld.demo.codec;


/**
 * Encoder. Turns a LoopBackTimeStamp object into a byte array that can be
 * transmitted client <--> server.
 */
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
 
import com.helloworld.demo.LoopBackTimeStamp;
 
public class TimeStampEncoder extends MessageToByteEncoder<LoopBackTimeStamp> {
  @Override
  protected void encode(ChannelHandlerContext ctx, LoopBackTimeStamp msg, ByteBuf out) throws Exception {
    out.writeBytes(msg.toByteArray());
  }
}