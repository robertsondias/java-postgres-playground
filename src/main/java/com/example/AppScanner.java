package com.example;

public class AppScanner {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.setRenda(-1000);
        cliente.setSexo('M');
        cliente.setAnoNascimento(1981);
        cliente.setNome("Robertson Dias");
        cliente.setCpf("99999999999");
        cliente.setCidade("Palmas");

        System.out.println("Renda: " + cliente.getRenda());
        System.out.println("Sexo: " + cliente.getSexo());
        System.out.println("Ano de Nascimento: " + cliente.getAnoNascimento());
        System.out.println("Cliente é especial: " + cliente.isEspecial());
        System.out.println("Nome Cliente 1: " + cliente.getNome());              
    }
}
