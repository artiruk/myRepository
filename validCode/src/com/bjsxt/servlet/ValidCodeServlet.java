package com.bjsxt.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ValidCodeServlet
 */
@WebServlet("/validcode")
public class ValidCodeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//创建一张图片
		BufferedImage image =new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);
		Graphics2D gra = image.createGraphics();
		gra.setColor(Color.WHITE);
		gra.fillRect(0, 0, 200, 100);
		List<Integer> randlist=new ArrayList<>();
		Random rnd=new Random();
		for(int i=0;i<4;i++){
			randlist.add(rnd.nextInt(10));
		}
		Color[] colors=new Color[]{Color.ORANGE,Color.BLACK,Color.BLUE,Color.GREEN,Color.RED,Color.GRAY};
		gra.setFont(new Font("宋体", Font.ITALIC|Font.BOLD, 50));
		for(int i=0;i<4;i++){
			gra.setColor(colors[rnd.nextInt(colors.length)]);
			gra.drawString(randlist.get(i)+"", i*50+rnd.nextInt(36)-5, 55+rnd.nextInt(41));
			
		}
		for(int i=0;i<2;i++){
			gra.setColor(colors[rnd.nextInt(colors.length)]);
			gra.drawLine(0, rnd.nextInt(61)+20, 200, rnd.nextInt(61)+20);
		}
		ServletOutputStream outputStream = resp.getOutputStream();
		ImageIO.write(image, "jpg", outputStream);
		
		HttpSession session=req.getSession();
		session.setAttribute("code", ""+randlist.get(0)+randlist.get(1)+randlist.get(2)+randlist.get(3));
	}
       
}
