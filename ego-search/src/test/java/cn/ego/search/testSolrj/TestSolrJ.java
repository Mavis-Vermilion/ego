package cn.ego.search.testSolrj;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestSolrJ {

	private SolrServer server = new HttpSolrServer("http://192.168.2.172:8080/solr");
	
	
	
	@Test
	public void TestSolrjAdd() throws SolrServerException, IOException {
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", "123456");
		doc.addField("title", "titilesdf");
		server.add(doc);
		server.commit();
	}
	
	@Test
	public void TestSolrjQuery() throws SolrServerException {
		SolrQuery query = new SolrQuery("*:*");
		query.setStart(0);
		query.setRows(5);
		QueryResponse response = server.query(query);
		int status = response.getStatus();
		System.out.println(status);
		SolrDocumentList documentList = response.getResults();
		for (SolrDocument solrDocument : documentList) {
			Iterator<Entry<String, Object>> iterator = solrDocument.iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				System.out.println(entry.getKey()+"---"+entry.getValue());
				
			}
		}
		
	}
	
	@Test
	public void TestSolrjQueryPage() throws SolrServerException {
		SolrQuery query = new SolrQuery("手机");
		query.setParam("df","item_keywords");
		query.setStart(0);
		query.setRows(5);
		QueryResponse response = server.query(query);
		int status = response.getStatus();
		System.out.println(status);
		SolrDocumentList documentList = response.getResults();
		System.out.println(documentList.getNumFound());
		System.out.println(documentList.size());
		for (SolrDocument solrDocument : documentList) {
			Iterator<Entry<String, Object>> iterator = solrDocument.iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				System.out.println(entry.getKey()+"---"+entry.getValue());
				
				
			}
			System.out.println();
		}
		
	}
}
