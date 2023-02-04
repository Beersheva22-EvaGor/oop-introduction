package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.HashMap;

class HashMapTest extends MapTest {

	@Override
	@BeforeEach
	void setUp() throws Exception {
		map= new HashMap<>();
		super.setUp();
	}


}
