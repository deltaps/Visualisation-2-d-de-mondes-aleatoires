package model;

import java.util.Random;

public class AdvanceNoise{

   private Random rand_;
   /** Amount of roughness */
   float roughness_;
   /** Plasma fractal grid */
   private float[][] grid_;

   private int width;
   private int height;
   /** Generate a noise source based upon the midpoint displacement fractal.
    *
    * @param rand The random number generator
    * @param roughness a roughness parameter
    * @param width the width of the grid
    * @param height the height of the grid
    */
   public AdvanceNoise(Random rand, float roughness, int width, int height, int seed) {
      this.width = width;
      this.height = height;
      grid_ = new float[width][height];
      rand_ = (rand == null) ? new Random(seed) : rand;
   }
   public void initialise() {
      int xh = grid_.length - 1;
      int yh = grid_[0].length - 1;
      for(int i = 0; i < this.width; i++){
         for(int j = 0; j < this.height; j++){
            this.grid_[i][j] = this.rand_.nextFloat();
         }
      }
   }

   public boolean[][] toBooleans() {
      int w = grid_.length;
      int h = grid_[0].length;
      boolean[][] ret = new boolean[w][h];
      for(int i = 0;i < w;i++) {
         for(int j = 0;j < h;j++) {
            ret[i][j] = grid_[i][j] < 0;
         }
      }
      return ret;
   }

   public float[][] getGrid_() {
      return grid_;
   }
}