package simcg.engine;

import java.awt.KeyEventDispatcher;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Controls implements KeyEventDispatcher, FocusListener {
	private static Controls			instance	= new Controls();

	private Map<Integer, Boolean>	keyMap		= new HashMap<>();

	public static Controls getInstance() {
		return instance;
	}

	public boolean isKeyPressed(int keycode) {
		Boolean pressed = keyMap.get(keycode);

		if (pressed == null)
			return false;
		else
			return pressed;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent ke) {
		if (ke.getID() == KeyEvent.KEY_PRESSED) {
			keyMap.put(ke.getKeyCode(), true);

			if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
				MainFrame.getInstance().dispose();
				System.exit(0);
			}

		}
		if (ke.getID() == KeyEvent.KEY_RELEASED) {
			keyMap.put(ke.getKeyCode(), false);
		}

		return false;
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
		for (Entry<Integer, Boolean> entry : keyMap.entrySet()) {
			entry.setValue(false);
		}
	}
}
