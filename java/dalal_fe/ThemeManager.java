import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

public class ThemeManager
{
	public static Theme systemTheme;
	public static Theme currentTheme;
	public static Theme redTheme;
	public static Theme blackTheme;
	public ThemeManager()
	{
		systemTheme=new Theme();
		systemTheme.colorTabBackground=Application.display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);
		systemTheme.colorWindowBackground=Application.display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);
		systemTheme.colorTabForeground=Application.display.getSystemColor(SWT.COLOR_TITLE_FOREGROUND);
		systemTheme.colorGradient= new Color[]{Application.display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND),
				Application.display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND),
				Application.display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT),
				Application.display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT)};
		systemTheme.colorPercent=new int[] {50, 65, 80};



		redTheme=new Theme();
		redTheme.colorWindowBackground=Application.display.getSystemColor(SWT.COLOR_GREEN);
		redTheme.colorTabBackground=Application.display.getSystemColor(SWT.COLOR_DARK_MAGENTA);
		redTheme.colorTabForeground=Application.display.getSystemColor(SWT.COLOR_WHITE);
		redTheme.colorGradient= new Color[]{Application.display.getSystemColor(SWT.COLOR_RED),
				Application.display.getSystemColor(SWT.COLOR_DARK_RED),
				Application.display.getSystemColor(SWT.COLOR_RED),
				Application.display.getSystemColor(SWT.COLOR_RED)};
		redTheme.colorPercent=new int[] {50, 65, 80};

		blackTheme=new Theme();
		blackTheme.colorWindowBackground=new Color(Application.display,230,230,230);
		blackTheme.colorTabBackground=new Color(Application.display,146,146,146);
		blackTheme.colorTabForeground=Application.display.getSystemColor(SWT.COLOR_WHITE);
		blackTheme.colorGradient= new Color[]{Application.display.getSystemColor(SWT.COLOR_BLACK),
				Application.display.getSystemColor(SWT.COLOR_BLACK),
				Application.display.getSystemColor(SWT.COLOR_BLACK),
				Application.display.getSystemColor(SWT.COLOR_BLACK)};
		blackTheme.colorPercent=new int[] {50, 65, 80};

		currentTheme=systemTheme;
	}
}
