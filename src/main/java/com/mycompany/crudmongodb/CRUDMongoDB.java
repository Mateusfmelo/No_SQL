package com.mycompany.crudmongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class CRUDMongoDB {
    
    // MÉTODO PARA CRIA A CONEXÃO COM MONGODB
    public static MongoClient criarConexao() {
        System.out.println("CONEXÃO REALIZADA COM MONGODB");
        
        MongoClient mongo = null;
        
        mongo = new MongoClient("localhost", 27017);
        
        return mongo;
    }
    
    // MÉTODO PARA INSERIR UM REGISTRO
    public static void inserirUsuario(DB db, String colecao, String nome, String pais) {
        DBCollection colec = db.getCollection(colecao);
        
        // CRIA UM REGISTRO E INSERE A INFORMAÇÃO RECIBIDA
        BasicDBObject documento = new BasicDBObject();
        documento.put("nome", nome);
        documento.put("pais", pais);
        
        colec.insert(documento);
        
    }
    
    // MOSTRA TODOS OS REGISTROS DA COLEÇÃO USUÁRIOS
    public static void mostrarColecao(DB db, String colecao) {
        DBCollection colec = db.getCollection(colecao);
        
        DBCursor cursor = colec.find();
        
        while(cursor.hasNext()) {
            System.out.println("* "+ cursor.next().get("nome") + " - " + cursor.curr().get("pais"));
        }
    }
    
    // MOSTRA DOS OS REGISTRO DA COLEÇÃO DO USUARIOS QUE COINCIDAM COM O NOME
    public static void buscarPorNome(DB db, String colecao, String nome) {
        DBCollection colect = db.getCollection(colecao);
        
        // CRIAÇÃO DA CONSULTA COM O NOME "NOME"
        BasicDBObject consulta = new BasicDBObject();
        consulta.put("nome", nome);
        
        // BUSCA E EXIBE TODOS OS REGISTROS QUE COINDIDAM COM A CONSULTA
        DBCursor cursor = colect.find(consulta);
        while(cursor.hasNext()) {
            System.out.println("* " + cursor.next().get("nome") + " - " + cursor.curr().get("pais"));
        }
    }
    
    // MÉTODO PARA ATUALIZAR UMREGISTRO
    public static void atualizarDocumento(DB db, String colecao, String nome) {
        DBCollection colec = db.getCollection(colecao);
        
        // SETA INFORMAÇÃO A ATUALIZAR
        BasicDBObject atualizarPais = new BasicDBObject();
        atualizarPais.append("$set", new BasicDBObject().append("pais", "Peru"));
        
        // BUSCA UM DOCUMENTO NA COLEÇÃO
        BasicDBObject buscarPorNome = new BasicDBObject();
        buscarPorNome.append("nome", nome);
        
        // REALIZA A ATUALIZAÇÃO
        colec.updateMulti(buscarPorNome, atualizarPais);
    }
    
    // MÉTODO PARA ELIMINAR UM REGISTRO
    public static void deletarDocumento(DB db, String colecao, String nome) {
        DBCollection colec = db.getCollection(colecao);
        
        colec.remove(new BasicDBObject().append("pais", nome));
    }
}
