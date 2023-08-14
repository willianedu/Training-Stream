package Program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Funcionario;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Diga o caminho do arquivo:");
		String path = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			List<Funcionario> list = new ArrayList<>();
			
			String line = br.readLine();
			
			while( line != null) {
				String[] fields = line.split(",");
				list.add(new Funcionario(fields[0],fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			} 
			
			System.out.print("Diga o salário: ");
			double x = sc.nextDouble();
			
			List<String> emails = list.stream().filter(p -> p.getSalario() > x).map(p -> p.getEmail()).sorted().collect(Collectors.toList());
			emails.forEach(System.out::println);
			
			double soma = list.stream().filter(p -> p.getNome().charAt(0) == 'M').map(p -> p.getSalario()).reduce(0.0, (z,y) -> z + y);
			System.out.println("Soma total do salário cujo os funcionários iniciam com a letra 'M': " + String.format("%.2f", soma));
			
			
			
		} catch (IOException e) {
			
			System.out.println("Error: " + e.getMessage());
			
		}
		
		
		
		
		sc.close();

	}

}
