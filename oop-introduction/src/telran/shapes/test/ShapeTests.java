package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.shapes.Canvas;
import telran.shapes.Directions;
import telran.shapes.Rectangle;
import telran.shapes.Shape;
import telran.shapes.Square;
import telran.shapes.SquareLeftTriangle;
import telran.shapes.SquareRightTriangle;

class ShapeTests {
	
	Canvas canvas = new Canvas(10, 20,
			new Shape[] { new Rectangle(10, 3), new Square(10), new SquareLeftTriangle(10) });
	Shape[] shapes = { new Rectangle(10, 3), new Square(10), new SquareLeftTriangle(10), new SquareRightTriangle(10),
			canvas, new Square(10) };
	
	@Test
	@Disabled
	void canvasInRowTest() {
		Canvas canvas = new Canvas(10, 4, shapes);
		canvas.setMargin(3);
		canvas.setDirection(Directions.ROW);
		displayStrings(canvas.presentation(2));

	}

	@Test
	@Disabled
	void canvasInColumnTest() {
		Canvas canvas = new Canvas(10, 4, shapes);
		canvas.setDirection(Directions.COLUMN);
		this.canvas.setDirection(Directions.ROW);
		canvas.setMargin(1);
		displayStrings(canvas.presentation(2));
	}
	
	@Test
	//@Disabled
	void canvasNestedTest() {
		// test for more complicated nesting
		Canvas canvasC1 = new Canvas(10, 20, new Shape[] { new Rectangle(5, 3), new Square(5)});
		Canvas canvasC2 = new Canvas(10, 20, new Shape[] { new Rectangle(10, 3), new SquareLeftTriangle(10) , canvasC1});
		Shape[] shapesC = { new Square(10), new SquareRightTriangle(10),
				canvasC2, new Square(10) };
		Canvas canvasC = new Canvas(10, 4, shapesC);
		canvasC.setDirection(Directions.COLUMN);
		canvasC1.setDirection(Directions.ROW);
		canvasC2.setDirection(Directions.ROW);

		System.out.println("P R E S E N T A T I O N");
		displayStrings(canvasC.presentation(2));
		System.out.println("A F T E R");
		System.out.println("Nested canvas have row direction");
		displayStrings(canvasC1.presentation(2));
		System.out.println("Nested canvas have row direction");
		displayStrings(canvasC2.presentation(2));
	}
	
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
	@Disabled
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