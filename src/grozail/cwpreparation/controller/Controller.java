package grozail.cwpreparation.controller;

import grozail.cwpreparation.model.longnumber.LongNumber;

import java.util.Collections;

/**
 * Created by grozail
 * on 27.12.16.
 */
public class Controller {

	private static Controller instance = new Controller();

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	private LongNumber longNumber;
	private Controller() {

	}

	public LongNumber getLongNumber() {
		return longNumber;
	}

	public void setLongNumber(LongNumber longNumber) {
		this.longNumber = longNumber;
	}
}
