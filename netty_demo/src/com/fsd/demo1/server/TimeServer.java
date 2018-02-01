package com.fsd.demo1.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TimeServer {
	public void bind(int port) throws InterruptedException {
		// 配置服务端的NIO线程组
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)// 实例化ServerSocketChannel
					.option(ChannelOption.SO_BACKLOG, 1024) // 设置serverSocketChannel的TCP参数
					.childHandler(new ChildChannelHandler());

			// 绑定端口，同步等待成功 ChannelFuture：代表异步I/O的结果
			ChannelFuture f = bootstrap.bind(port).sync();

			// 服务端监听端口关闭
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			System.out.println("TimeServer 启动失败");
		} finally {
			// 释放资源
			// workGroup.shutdownGracefully();
			// bossGroup.shutdownGracefully();
		}
	}

	private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
		
		public ChildChannelHandler() {
			System.out.println("this is test");
		}
		
		@Override
		protected void initChannel(SocketChannel arg0) throws Exception {
			arg0.pipeline().addLast(new TimeServerHandler());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		int port = 8080;
		if (args != null && args.length > 0) {
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				port = 8080;
			}
		}
		new TimeServer().bind(port);
	}
}
