package cn.ego.search.testSolrj;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

public class TestSolrCloudJ {

	//private SolrServer server = new HttpSolrServer("http://192.168.2.172:8080/solr");
	
	private CloudSolrServer server = null;
	
	@Before
	public void init() {
		//zookeeper 
		String zkHost = "192.168.2.172:2181,192.168.2.172:2182,192.168.2.172:2183";
		server = new CloudSolrServer(zkHost);
		server.setDefaultCollection("collection2");
	}
	
	@Test
	public void TestSolrjAdd() throws SolrServerException, IOException {
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", "123456");
		doc.addField("title", "sdfsfsfsadfasdf");
		server.add(doc);
		UpdateResponse response = server.commit();
		int status = response.getStatus();
		System.out.println(status);
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
	
	@Test
	public void TestSolrDelete() throws SolrServerException, IOException {
		server.deleteByQuery("*:*");
		server.commit();
		
		
	}
}
