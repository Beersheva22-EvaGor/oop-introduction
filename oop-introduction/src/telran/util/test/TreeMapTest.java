package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.TreeMap;

class TreeMapTest extends MapTest {

	@Override
	@BeforeEach
	void setUp() throws Exception {
		map= new TreeMap<>();
		super.setUp();
	}


}