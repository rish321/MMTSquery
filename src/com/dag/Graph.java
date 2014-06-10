package com.dag;

import java.util.HashMap;

public class Graph {

	//public int numNodes = 0;
	public HashMap<String, Struct> graph;

	Graph()
	{
		graph = new HashMap<String, Struct>();
	}
	
	public boolean existNode(String node)
	{
		if(graph.get(node) == null)
			return false;
		return true;
	}
	
	public void addNode(String node)
	{
		if(graph.get(node) == null)
		{
			Struct data = new Struct();
			graph.put(node, data);
		}
	}
	public void addLink(String node1, String node2)
	{
		Struct data = graph.get(node1);
		data.set.add(node2);
		graph.put(node1, data);
	}
	public void addClass(String node, String clas)
	{
		graph.get(node).clas = clas;
	}
	public String getClass(String node)
	{
		return graph.get(node).clas;
	}
	public boolean explored(String node) {
		if(!existNode(node))
			return false;
		if(graph.get(node).iTime == -1)
		{
			graph.get(node).iTime = 1;
			return false;
		}
		else
			return true;
	}
}
