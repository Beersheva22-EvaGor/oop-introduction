package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.LinkedHashSet;

class LinkedHashSetTest extends SetTest{

	@BeforeEach
	@Override 
	void setUp() throws Exception  {
		collection = new LinkedHashSet<>();
		super.setUp();
	}

}
