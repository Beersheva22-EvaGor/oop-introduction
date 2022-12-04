package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.shapes.Rectangle;
import telran.shapes.Square;
import telran.shapes.SquareLeftTriangle;
import telran.shapes.SquareRightTriangle;
import telran.shapes.SquareTriangle;

class ShapeTests {

	@Test
	@Disabled
	void rectangleTest() {
		Rectangle rectangle = new Rectangle(10, 5);
		assertEquals(10, rectangle.getWidth());
		assertEquals(5, rectangle.getHeight());
		displayStrings(rectangle.presentation(20));
		Rectangle.setSymbol("#");
		displayStrings(rectangle.presentation(20));
	}
	
	@Test
	@Disabled
	void SquareTest() {
		Square square = new Square(10);
		assertEquals(10, square.getWidth());
		assertEquals(10, square.getHeight());
		Square.setSymbol("#");
		displayStrings(square.presentation(20));
	}
	
	@Test
	@Disabled
	void SquareTriangleTest() {
		SquareTriangle triangleLeft = new SquareTriangle(10, true);
		assertEquals(10, triangleLeft.getWidth());
		assertEquals(10, triangleLeft.getHeight());
		SquareTriangle.setSymbol("*");
		displayStrings(triangleLeft.presentation(20));
		SquareTriangle triangleRight = new SquareTriangle(10, false);
		assertEquals(10, triangleRight.getWidth());
		assertEquals(10, triangleRight.getHeight());
		SquareTriangle.setSymbol("*");
		displayStrings(triangleRight.presentation(20));
	}
	
	@Test
	@Disabled
	void SquareRightTriangleTest() {
		SquareRightTriangle triangleRight = new SquareRightTriangle(10);
		assertEquals(10, triangleRight.getWidth());
		assertEquals(10, triangleRight.getHeight());
		displayStrings(triangleRight.presentation(20));
	}
	@Test
	@Disabled
	void SquareLeftTriangleTest() {
		SquareLeftTriangle triangleLeft = new SquareLeftTriangle(10);
		assertEquals(10, triangleLeft.getWidth());
		assertEquals(10, triangleLeft.getHeight());
		displayStrings(triangleLeft.presentation(20));
	}
	
	private void displayStrings(String strings[]) {
		for(String str: strings) {
			System.out.println(str);
		}
	}
	
	@Test
	void SquareBreakRulesTest() {
		Square square = new Square(10);
		 square.setHeight(5);
		 displayStrings(square.presentation(10));
		 System.out.println();
		 
		 square.setWidth(15);
		 displayStrings(square.presentation(10));
	}

}