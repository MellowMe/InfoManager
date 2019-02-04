package cn.yufan.infomanager.conctroller;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class VisitCount implements ServletRequestListener {
	public static int count = 0;
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		count++;
	}
//	private VisitCount(){}
}
