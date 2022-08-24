package com.mycompany.crudmongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import static com.mycompany.crudmongodb.CRUDMongoDB.deletarDocumento;
import static com.mycompany.crudmongodb.CRUDMongoDB.atualizarDocumento;
import static com.mycompany.crudmongodb.CRUDMongoDB.buscarPorNome;
import static com.mycompany.crudmongodb.CRUDMongoDB.mostrarColecao;
import static com.mycompany.crudmongodb.CRUDMongoDB.inserirUsuario;
import static com.mycompany.crudmongodb.CRUDMongoDB.criarConexao;

public class main {
    public static void main(String[]args) {
        
        MongoClient mongo = criarConexao();
        
        //SE A BASE DE DADOS NÃO EXISTIR, SERÁ CRIADA
        if(mongo != null) {
            DB db = mongo.getDB("Cadastro");
            
            System.out.println("BASE DE DADOS CRIADA");
            
            inserirUsuario(db, "usuarios", "Sergio", "Mexico");
            inserirUsuario(db, "usuarios", "Laura", "Colombia");
            inserirUsuario(db, "usuarios", "Franco", "Chile\n");
    
            System.out.println("\nINSERINDO USUÁRIOS");
            
            mostrarColecao(db, "usuarios");
            
            System.out.println("BUSCANDO USUÁRIO POR NOME");
            buscarPorNome(db, "usuarios", "Sergio");
            
            System.out.println("\nANTES DE ATUALIZAR");
            mostrarColecao(db, "usuarios");
            atualizarDocumento(db, "usuarios", "Sergio");
            System.out.println("DEPOIS DE ATUALIZAR");
            mostrarColecao(db, "usuarios");
            
            System.out.println("ANTES DE DELETAR");
            mostrarColecao(db, "usuarios");
            deletarDocumento(db, "usuarios", "Colombia");
            System.out.println("DEPOIS DE DELETAR");
            mostrarColecao(db, "usuarios");
            
        }
        
    }
    
}