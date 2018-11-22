package com.simple.lucene;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.javassist.expr.NewArray;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import com.simple.po.Blog;
import com.simple.util.DateUtil;
import com.simple.util.StringUtil;

/**
 * ����������
 * @author Administrator
 *
 */
public class BlogIndex {

	private Directory dir;

	/**
	 * ��ȡIndexWriterʵ��
	 * @return
	 * @throws Exception
	 */
	private IndexWriter getWriter()throws Exception{
		//dir=FSDirectory.open(Paths.get("/home/lucene/blog"));
		dir=FSDirectory.open(Paths.get("D:\\shiro\\blogShiro"));
		SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
		IndexWriterConfig iwc=new IndexWriterConfig(analyzer);
		IndexWriter writer=new IndexWriter(dir, iwc);
		return writer;
	}
	
	/**
	 * ��ʼ������Lucene
	 * @throws Exception 
	 */
	public void IndexManager(List<Blog> list) throws Exception {
		//�½���ѯ��
		List<Document> doList = new ArrayList<>();
		System.out.println("���"+list.size());
		Document document;
		for(Blog blog : list) {
			document = new Document();
			// ���� id 
			//���ִ� ���� �洢 
			System.out.println(blog.getId());
			Field id = new StringField("id", blog.getId().toString(), Store.YES);
			// ���ͱ���
			// �ִʡ��������洢 TextField
			System.out.println(blog.getTitle());
			Field name = new TextField("title", blog.getTitle(), Store.YES);
			//�������� �ִ����� ���洢 ������洢 �洢�����Ǹ��ޱ�ǩ��
			//Field content = new TextField("content", blog.getContent(), Store.NO);
			//��������������ҳ��ǩ
			//�ִ������洢
			System.out.println(blog.getContentNoTag());
			Field ContentNoTag = new TextField("ContentNoTag",blog.getContentNoTag(),Field.Store.YES);
			//���͹ؼ���
			//�ִ��������洢
			System.out.println(blog.getKeyWord());
			Field keyWord = new TextField("keyWord", blog.getKeyWord(), Store.NO);
			// ����boostֵ
			//if (blog.getId() == 4)
			//id.setBoost(100f);
			document.add(id);
			document.add(name);
			document.add(ContentNoTag);
			document.add(keyWord);
			//��document����ŵ� doclist����
			doList.add(document);
			}
			//5.3.1
			//indexWriter �����װ����
			IndexWriter writer = getWriter();
			for(Document document1:doList) {
				writer.addDocument(document1);
			}
			writer.close();
	}
	
	/**
	 * ��Ӳ�������
	 * @param blog
	 * @throws Exception
	 */
	public void addIndex(Blog blog )throws Exception{
		IndexWriter writer=getWriter();
		Document doc=new Document();
		doc.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));
		doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));
		doc.add(new StringField("releaseDate",DateUtil.formatDate(new Date(), "yyyy-MM-dd"),Field.Store.YES));
		doc.add(new TextField("content",blog.getContentNoTag(),Field.Store.YES));
		writer.addDocument(doc);
		writer.close();
	}
	
	/**
	 * ���²�������
	 */
	public void updateIndex(Blog blog)throws Exception{
		IndexWriter writer=getWriter();
		Document doc=new Document();
		doc.add(new StringField("id",String.valueOf(blog.getId()),Field.Store.YES));
		doc.add(new TextField("title",blog.getTitle(),Field.Store.YES));
		doc.add(new StringField("releaseDate",DateUtil.formatDate(new Date(), "yyyy-MM-dd"),Field.Store.YES));
		doc.add(new TextField("content",blog.getContentNoTag(),Field.Store.YES));
		writer.updateDocument(new Term("id",String.valueOf(blog.getId())), doc);
		writer.close();
	}
	
	
	/**
	 * ɾ��ָ�����͵�����
	 * @param blogId,String RootPath
	 * @throws Exception
	 */
	public void deleteIndex(String blogId)throws Exception{
		IndexWriter writer=getWriter();
		writer.deleteDocuments(new Term("id",blogId));
		writer.forceMergeDeletes(); // ǿ��ɾ��
		writer.commit();
		writer.close();
	}
	
	
	
	/**
	 * ��ѯ������Ϣ
	 * @param q
	 * @return
	 * @throws Exception
	 */
	public List<Blog> searchBlog(String q)throws Exception{
		//dir=FSDirectory.open(Paths.get("/home/lucene/blog"));
		dir=FSDirectory.open(Paths.get("D:\\shiro\\blogShiro"));
		IndexReader reader=DirectoryReader.open(dir);
		IndexSearcher is=new IndexSearcher(reader);
		BooleanQuery.Builder booleanQuery=new BooleanQuery.Builder();
		SmartChineseAnalyzer analyzer=new SmartChineseAnalyzer();
		QueryParser parser=new QueryParser("title", analyzer);
		Query query=parser.parse(q);
		
		QueryParser parser2=new QueryParser("content", analyzer);
		Query query2=parser2.parse(q);
		
		
		booleanQuery.add(query, BooleanClause.Occur.SHOULD);
		booleanQuery.add(query2, BooleanClause.Occur.SHOULD);
		
		TopDocs hits=is.search(booleanQuery.build(), 100);
		QueryScorer scorer=new QueryScorer(booleanQuery.build());
		Fragmenter fragmenter=new SimpleSpanFragmenter(scorer);
		SimpleHTMLFormatter simpleHTMLFormatter=new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
		Highlighter highlighter=new Highlighter(simpleHTMLFormatter, scorer);
		highlighter.setTextFragmenter(fragmenter);
		
		List<Blog> blogList=new LinkedList<Blog>();
		for(ScoreDoc scoreDoc:hits.scoreDocs){
			Document doc=is.doc(scoreDoc.doc);
			Blog blog=new Blog();
			blog.setId(Integer.parseInt(doc.get("id")));
			blog.setReleaseDateStr(doc.get("releaseDate"));
			String title=doc.get("title");
			if(title!=null){
				TokenStream tokenStream=analyzer.tokenStream("title", new StringReader(title));
				String hTitle=highlighter.getBestFragment(tokenStream, title);
				if(StringUtil.isEmpty(hTitle)){
					blog.setTitle(title);
				}else{
					blog.setTitle(hTitle);
				}
			}
			//���ǵ�html�е�<��ǩ>
			
			String content=doc.get("content");//���contentȡ����  notag��content  �����е�
			//��<>ת���   &lt; <    &gt; >
			content = content.replace("<", "&lt;");
			content =  content.replace(">", "&gt;");
			//String content= doc.get("content");
			if(content!=null){
				TokenStream tokenStream=analyzer.tokenStream("content", new StringReader(content));
				String hContent=highlighter.getBestFragment(tokenStream, content);
				
				if(StringUtil.isEmpty(hContent)){
					if(content.length()<=200){
						blog.setContent(content);						
					}else{
						blog.setContent(content.substring(0, 200));	
					}
				}else{
					blog.setContent(hContent);
				}
			}
			blogList.add(blog);
		}
		return blogList;
	}
	
}
