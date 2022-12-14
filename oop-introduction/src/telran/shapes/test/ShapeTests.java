package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.shapes.Canvas;
import telran.shapes.Directions;
import telran.shapes.Rectangle;
import telran.shapes.Shape;
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
		for (String str : strings) {
			System.out.println(str);
		}
	}

	@Test
	void CanvasTest() {
		Rectangle rect = new Rectangle(3, 5);
		SquareRightTriangle triangleRight = new SquareRightTriangle(10);
		SquareLeftTriangle triangleLeft = new SquareLeftTriangle(5);

		Canvas canva = new Canvas(20, 5, new Shape[] { rect, triangleRight, triangleLeft });
		canva.setDirection(Directions.COLUMN);
		canva.setMargin(2);

		System.out.println("C A N V A S :");
		System.out.println("Vertical presenation:");
		displayStrings(canva.presentation(5));

		canva.setDirection(Directions.ROW);
		canva.setMargin(2);

		System.out.println("Horizontal presenation:");
		displayStrings(canva.presentation(20));

	}

}