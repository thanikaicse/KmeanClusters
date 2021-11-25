package Clustering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		// double[][] data = {{1d,2d},{2d,4d},{1d,1d},{0d,0d},{2d,5d}, {2d,15d},
		// {4d,20d}};
		double[][] data = new double[200][2];
		int numClusters = 10;
		
		System.out.println("Enter Dataset Name:");		
		Scanner input = new Scanner(System.in);
		String DatasetName = input.nextLine().toString()+".csv";
		System.out.println("Enter the number of clusters");
		numClusters=input.nextInt();
		
		File file = new File(DatasetName);
		BufferedReader bufRdr = new BufferedReader(new FileReader(file));
		String line = null;
		int row = 0;
		int col = 0;

		// read each line of text file
		while ((line = bufRdr.readLine()) != null)// && row< 6 )
		{
			StringTokenizer st = new StringTokenizer(line, ",");
			while (st.hasMoreTokens()) {
				// get next token and store it in the array
				// numbers[row][col] = st.nextToken();
				data[row][col] = Double.valueOf(st.nextToken());
				col++;
			}
			col = 0;
			row++;
		}

		Kmeans kmeans = new Kmeans(data, numClusters);
		// kmeans.setEpsilon(0.000001);
		kmeans.calculateClusters();

		ArrayList[] clusters = kmeans.getClusters();
		int m = 1;
		for (ArrayList cluster : clusters) {
			System.out.println("CLUSTER " + m);
			m++;
			for (Object dat : cluster) {
				for (int i = 0; i < 2; i++)
					System.out.print(((double[]) dat)[i] + " ");
				System.out.println();
			}
			System.out.println("\n***************\n");
		}

		System.out.println(kmeans.getClusterVars()[0]);
		System.out.println(kmeans.getClusterVars()[1]);
		System.out.println(kmeans.getTotalVar());
		System.out.println(kmeans.getClusterCenters()[0][0] + " "
				+ kmeans.getClusterCenters()[0][1]);
		System.out.println(kmeans.getClusterCenters()[1][0] + " "
				+ kmeans.getClusterCenters()[1][1]);
	}

}

