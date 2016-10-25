package sim.tools;

import java.util.ArrayList;
public class Calculations {
	public static double[] rotate(double[] points, Axis axis, double radians){
		int i = 0, j = 1;
		double[] a = {points[0], points[1], points[2]};
		switch (axis){
		case Z:break;
		case X:
			 i = 1;
			 j=2;
			break;
		case Y:
			i = 0;
			j = 2;
			break;
		} 
		a[i] = (points[i]*Math.cos(radians)) + (points[j]*-Math.sin(radians));
		a[j] = (points[i]*Math.sin(radians)) + (points[j]*Math.cos(radians));
		return a;
	}
	public static int vectorMinIndex(double vector[]){
		int min =0;
		for(int k = 0; k<vector.length; k++)
			if(vector[k]<vector[min])
				min = k;
		return min;
	}
	public static int vectorMaxIndex(double[] vector){
		int max = 0;
		for(int k = 0; k<vector.length; k++)
			if(vector[k]>vector[max])
				max = k;
		return max;
	}
	public static double[] rotate(double[] vector, Axis axis, int angle){
		return rotate(vector, axis, Math.toRadians(angle));
	}
	public static double fromRads(double rads){
		return 180/Math.PI * rads;
	}
	public static double magnitude(double[] vector){
		double sum = 0;
		for(int k = 0; k<vector.length; k++)
			sum += (vector[k]*vector[k]);
		return Math.sqrt(sum);
	}
	public static double[] unitVector(double[] vector){
		double[] vectorPrime = new double[vector.length];
		double magnitude = magnitude(vector);
		for(int k =0; k<vector.length; k++)
			vectorPrime[k]/=magnitude;
		return vectorPrime;
	}
	public static double[][] drawLine(double[] v1, double[] v2){
		if(v1.length == v2.length){
			int dimensions = v1.length;
			double[] d = new double[dimensions]; //distances
			double[] s = new double[dimensions]; // step size
			for(int k = 0 ;k < dimensions; k++)
				d[k] = Math.abs(v2[k] - v1[k]);
			
			double maxDistance = d[0];
			for(int k  = 0; k<dimensions; k++){
				if(d[k]>maxDistance)
					maxDistance = d[k];
			}
			
			for(int k  = 0; k<dimensions; k++){
				s[k] = d[k]/maxDistance;
			}
			ArrayList<double[]> matrix = new ArrayList<double[]>();
			double[] current = v1.clone();
			for(int k = 0; k<maxDistance; k++){
				for(int i = 0; i<dimensions; i++){
					if(v1[i]<v2[i])
					current[i]= current[i] + s[i];
					else if(v1[i]>v2[i])
						current[i]= current[i] - s[i];
				}
				double[] vec = new double[dimensions];
				for(int  i = 0; i<dimensions;i++)
					vec[i] = Math.round(current[i]);
				matrix.add(vec);
			}
			return matrix.toArray(new double[matrix.size()][]);
		}else
			System.out.println("invalid vectors");
		return null;
	}
    public static double sigmoid(double x){
        return 1/(1+Math.exp(-x));
    }
    public static double sigmoidPrime(double x){
        return sigmoid(x)*(1-sigmoid(x));
    }
    public static double[] crossProduct(double[] v1, double[] v2){
        if(v1.length!=v2.length){
            System.out.println("Vectors must be same length");
            return null;
        }
        double[] product = new double[v1.length];
        for(int k = 0; k<v1.length; k++)
            product[k] = v1[k]*v2[k];
        return product;
    }
    public static double summVector(double[] v){
        double sum = 0;
        for(int k = 0; k<v.length; k++)
            sum+= v[k];
        return sum;
 
    }
    public static boolean checkMatrix(double[][] matrix){
        for(int k = 0; k<matrix.length; k++)
            if(matrix[k].length != matrix[0].length)
                return false;
            return true;
    }
    public static double dotProduct(double[] v1, double[] v2){
        return summVector(crossProduct(v1,v2));
    }
    public static double[] vectorXMatrix(double[] v, double[][] m){
        if(!checkMatrix(m)){
            System.out.println("Unable to multiply vector X matrix, matrix invalid");
            return null;
        }
        else if(m[0].length!= v.length){
            System.out.println("Unable to multple 1x" + v.length + " * " + m.length + "x" + m[0].length);
            return null;
        }
        double[] product = new double[v.length];
        for(int k = 0; k<m.length; k++){
            for(int i = 0; i<v.length; i++)
                product[k] += m[k][i] * v[i];
        }
        return product;
    }
}
