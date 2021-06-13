
/*************************************************************************
 *  Compilation:  javac ArtCollage.java
 *  Execution:    java ArtCollage Flo2.jpeg Flo.jpg Baloo.jpeg PlocLilo.jpg Ariel.jpg
 *
 *  @author: Wi11iamC
 *
 *************************************************************************/

import java.awt.Color;

public class ArtCollage {

    // The orginal picture
    private Picture original;

    // The collage picture
    private Picture collage;

    // The collage Picture consists of collageDimension X collageDimension tiles
    private int collageDimension;

    // A tile consists of tileDimension X tileDimension pixels
    private int tileDimension;

    /*
     * One-argument Constructor 1. set default values of collageDimension to 4 and
     * tileDimension to 100 2. initializes original with the filename image 3.
     * initializes collage as a Picture of tileDimension*collageDimension x
     * tileDimension*collageDimension, where each pixel is black (see all
     * constructors for the Picture class). 4. update collage to be a scaled version
     * of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage(String filename) {

        // WRITE YOUR CODE HERE
        this.collageDimension = 4;
        this.tileDimension = 100;

        this.original = new Picture(filename);

        this.collage = new Picture(this.tileDimension * this.collageDimension,
                this.tileDimension * this.collageDimension);

        int w = this.collage.width();
        int h = this.collage.height();
        for (int tcol = 0; tcol < w; tcol++)
            for (int trow = 0; trow < h; trow++) {
                int scol = tcol * this.original.width() / w;
                int srow = trow * this.original.height() / h;
                Color color = this.original.get(scol, srow);
                this.collage.set(tcol, trow, color);
            }

    }

    /*
     * Three-arguments Constructor 1. set default values of collageDimension to cd
     * and tileDimension to td 2. initializes original with the filename image 3.
     * initializes collage as a Picture of tileDimension*collageDimension x
     * tileDimension*collageDimension, where each pixel is black (see all
     * constructors for the Picture class). 4. update collage to be a scaled version
     * of original (see scaling filter on Week 9 slides)
     *
     * @param filename the image filename
     */
    public ArtCollage(String filename, int td, int cd) {

        // WRITE YOUR CODE HERE
        this.collageDimension = cd;
        this.tileDimension = td;

        this.original = new Picture(filename);

        this.collage = new Picture(this.tileDimension * this.collageDimension,
                this.tileDimension * this.collageDimension);

        int w = this.collage.width();
        int h = this.collage.height();
        for (int ti = 0; ti < w; ti++) {
            for (int tj = 0; tj < h; tj++) {
                int si = ti * this.original.width() / w;
                int sj = tj * this.original.height() / h;
                Color color = this.original.get(si, sj);
                this.collage.set(ti, tj, color);
            }
        }
    }

    /*
     * Returns the collageDimension instance variable
     *
     * @return collageDimension
     */
    public int getCollageDimension() {

        // WRITE YOUR CODE HERE
        return this.collageDimension;
    }

    /*
     * Returns the tileDimension instance variable
     *
     * @return tileDimension
     */
    public int getTileDimension() {

        // WRITE YOUR CODE HERE
        return this.tileDimension;
    }

    /*
     * Returns original instance variable
     *
     * @return original
     */
    public Picture getOriginalPicture() {

        // WRITE YOUR CODE HERE
        return this.original;
    }

    /*
     * Returns collage instance variable
     *
     * @return collage
     */
    public Picture getCollagePicture() {

        // WRITE YOUR CODE HERE
        return this.collage;
    }

    /*
     * Display the original image Assumes that original has been initialized
     */
    public void showOriginalPicture() {

        // WRITE YOUR CODE HERE
        this.original.show();
    }

    /*
     * Display the collage image Assumes that collage has been initialized
     */
    public void showCollagePicture() {

        // WRITE YOUR CODE HERE
        this.collage.show();
    }

    public Color getC(int col, int row) {
        return this.collage.get(col, row);
    }

    /*
     * Replaces the tile at collageCol,collageRow with the image from filename Tile
     * (0,0) is the upper leftmost tile
     *
     * @param filename image to replace tile
     * 
     * @param collageCol tile column
     * 
     * @param collageRow tile row
     */
    public void replaceTile(String filename, int collageCol, int collageRow) {

        // WRITE YOUR CODE HERE
        Picture replace = new Picture(filename);

        Picture temps = new Picture(this.tileDimension, this.tileDimension);

        int w = temps.width();
        int h = temps.height();
        for (int tcol = 0; tcol < w; tcol++)
            for (int trow = 0; trow < h; trow++) {
                int scol = tcol * replace.width() / w;
                int srow = trow * replace.height() / h;
                Color color = replace.get(scol, srow);
                temps.set(tcol, trow, color);
            }
        for (int cols = this.tileDimension * (collageCol); cols < this.tileDimension * (collageCol)
                + this.tileDimension; cols += this.tileDimension) {
            for (int rows = this.tileDimension * (collageRow); rows < this.tileDimension * (collageRow)
                    + this.tileDimension; rows += this.tileDimension) {
                for (int col = 0; col < w; col++) {
                    for (int row = 0; row < h; row++) {
                        this.collage.set(cols + col, rows + row, temps.get(col, row));
                    }

                }
            }
        }
    }

    /*
     * Makes a collage of tiles from original Picture original will have
     * collageDimension x collageDimension tiles, each tile has tileDimension X
     * tileDimension pixels
     */
    public void makeCollage() {

        // WRITE YOUR CODE HERE

        int w = this.tileDimension;
        int h = this.tileDimension;
        Picture target = new Picture(w, h);
        for (int ti = 0; ti < w; ti++) {
            for (int tj = 0; tj < h; tj++) {
                int si = ti * this.collage.width() / w;
                int sj = tj * this.collage.height() / h;
                Color color = this.collage.get(si, sj);
                target.set(ti, tj, color);
            }
        }

        for (int cols = 0; cols < this.collage.width(); cols += this.tileDimension) {
            for (int rows = 0; rows < this.collage.height(); rows += this.tileDimension) {
                for (int col = 0; col < w; col++) {
                    for (int row = 0; row < h; row++) {
                        this.collage.set(cols + col, rows + row, target.get(col, row));
                    }

                }
            }
        }

    }

    /*
     * Colorizes the tile at (collageCol, collageRow) with component (see CS111 Week
     * 9 slides, the code for color separation is at the book's website)
     *
     * @param component is either red, blue or green
     * 
     * @param collageCol tile column
     * 
     * @param collageRow tile row
     */
    public void colorizeTile(String component, int collageCol, int collageRow) {

        // WRITE YOUR CODE HERE

        int colStart = this.tileDimension * collageCol;
        int rowStart = this.tileDimension * collageRow;
        for (int col = colStart; col < this.tileDimension * collageCol + this.tileDimension; col++) {
            for (int row = rowStart; row < this.tileDimension * collageRow + this.tileDimension; row++) {
                Color color = this.collage.get(col, row);

                int r = component.toLowerCase().equals("red") ? color.getRed() : 0;
                int g = component.toLowerCase().equals("green") ? color.getGreen() : 0;
                int b = component.toLowerCase().equals("blue") ? color.getBlue() : 0;

                this.collage.set(col, row, new Color(r, g, b));
            }
        }

    }

    /*
     * Grayscale tile at (collageCol, collageRow) (see CS111 Week 9 slides, the code
     * for luminance is at the book's website)
     *
     * @param collageCol tile column
     * 
     * @param collageRow tile row
     */

    public void grayscaleTile(int collageCol, int collageRow) {

        // WRITE YOUR CODE HERE

        int colStart = this.tileDimension * collageCol;
        int rowStart = this.tileDimension * collageRow;
        for (int col = colStart; col < this.tileDimension * collageCol + this.tileDimension; col++) {
            for (int row = rowStart; row < this.tileDimension * collageRow + this.tileDimension; row++) {
                Color color = this.collage.get(col, row);
                Color gray = Luminance.toGray(color);
                this.collage.set(col, row, gray);
            }
        }

    }

    /*
     *
     * Test client: use the examples given on the assignment description to test
     * your ArtCollage
     */
    public static void main(String[] args) {

        ArtCollage art = new ArtCollage(args[0]);
        art.showCollagePicture();

        ArtCollage art2 = new ArtCollage(args[0], 200, 3);
        art2.showCollagePicture();

        ArtCollage art10 = new ArtCollage(args[0], 200, 2);
        art10.makeCollage();

        // Replace 3 tiles
        art10.replaceTile(args[1], 0, 1);
        art10.replaceTile(args[2], 1, 0);
        art10.replaceTile(args[3], 1, 1);
        art10.colorizeTile("green", 0, 0);
        art10.showCollagePicture();

        ArtCollage art7A = new ArtCollage(args[0], 200, 3);
        art7A.makeCollage();
        art7A.grayscaleTile(2, 0);
        // System.out.println("7A [r=36,g=36,b=36]: " + art7A.getC(400, 0));
        art7A.showCollagePicture();

    }
}