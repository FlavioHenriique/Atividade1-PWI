package com.mycompany.app;

import com.mycompany.controle.GerenciadorCliente;
import com.mycompany.controle.GerenciadorPedido;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Scanner;

public class app {

    public static void main(String[] args) {
        try {
            GerenciadorCliente gCliente = new GerenciadorCliente();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Digite 1 para adicionar um cliente.");
                System.out.println("Digite 2 para remover um cliente.");
                System.out.println("Digite 3 para atualizar os dados de um "
                        + "cliente");
                System.out.println("Digite 4 para gerenciar seus pedidos.");
                System.out.println("Digite 5 para sair.");
                System.out.println("Digite 6 para JDBC");
                int escolha = scanner.nextInt();
                switch (escolha) {
                    case 1: {
                        System.out.println("Digite o ID:");
                        int id = scanner.nextInt();
                        System.out.println("Digite o Nome:");
                        String nome = scanner.next();
                        System.out.println("Digite o documento:");
                        String documento = scanner.next();
                        System.out.println("Digite o saldo:");
                        double saldo = scanner.nextDouble();
                        System.out.println("Digite 1 para permanecer no estado "
                                + "de ativo ou 2 para permanecer inativo:");
                        int opcao = scanner.nextInt();
                        boolean ativo;
                        if (opcao == 1) {
                            ativo = true;
                        } else {
                            ativo = false;
                        }
                        if (gCliente.adicionaCliente(id, nome, documento, ativo,
                                saldo)) {
                            System.out.println("Cliente adicionado");
                        }
                        break;
                    }
                    case 2: {
                        System.out.println("Digite o id do cliente:");
                        int id = scanner.nextInt();
                        gCliente.removeCliente(id);
                        System.out.println("Deletado com sucesso");
                        break;
                    }
                    case 3: {
                        System.out.println("Digite o ID:");
                        int id = scanner.nextInt();
                        System.out.println("Digite o Nome:");
                        String nome = scanner.next();
                        System.out.println("Digite o documento:");
                        String documento = scanner.next();
                        System.out.println("Digite o saldo:");
                        double saldo = scanner.nextDouble();
                        System.out.println("Digite 1 para permanecer no estado "
                                + "de ativo ou 2 para permanecer inativo:");
                        int opcao = scanner.nextInt();
                        boolean ativo;
                        if (opcao == 1) {
                            ativo = true;
                        } else {
                            ativo = false;
                        }
                        if (gCliente.atualizaCliente(id, nome, documento, ativo,
                                saldo)) {
                            System.out.println("Cliente atualizado");
                        } else {
                            System.out.println("Erro ao atualizar");
                        }
                    }
                    break;
                    case 4: {
                        int saida = 0;

                        System.out.println("Digite seu ID;");
                        int id = scanner.nextInt();
                        GerenciadorPedido gPedido = new GerenciadorPedido();
                        while (saida != 4) {
                            System.out.println("Digite 1 para fazer um pedido:");
                            System.out.println("Digite 2 para remover um pedido:");
                            System.out.println("Digite 3 para atualizar um pedido:");
                            System.out.println("Digite 4 para sair");
                            saida = scanner.nextInt();
                            switch (saida) {
                                case 1: {
                                    System.out.println("Digite o id do pedido:");
                                    int idpedido = scanner.nextInt();
                                    float valor = 10;
                                    Date data = Date.valueOf("2012-12-12");
                                    int cliente = id;
                                    gPedido.adicionaPedido(idpedido, data, cliente, valor);
                                    System.out.println("Pedido registrado!");
                                    break;
                                }
                                case 2: {
                                    System.out.println("Digite o id do pedido:");
                                    int exclusao = scanner.nextInt();
                                    System.out.println("Será removido.");
                                    gPedido.removePedido(exclusao);
                                    break;
                                }
                                case 3: {
                                    System.out.println("Digite o id do pedido:");
                                    int idpedido = scanner.nextInt();
                                    float valor = 120;
                                    Date data = Date.valueOf("2017-10-03");
                                    int cliente = id;
                                    if (gPedido.atualizaPedido(idpedido, data, cliente, valor)) {
                                        System.out.println("Pedido atualizado!");
                                    } else {
                                        System.out.println("Não foi possível atualizar");
                                    }
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case 5: {
                        System.exit(0);
                    }
                    case 6:{
                        gCliente.jdbc();
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
