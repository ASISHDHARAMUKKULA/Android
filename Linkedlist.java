
public class Linkedlist {

	Node head;
	public void insert(int data)
	{
		Node node=new Node();
		node.data=data;
		
		if(head==null)
		{
			head=node;
		}
		else
		{
			Node n=head;
			while(n.next!=null)
			{
				n=n.next;
			}
			n.next=node;
		}
		
	}
	
	public void insertAtstart(int data){
		Node node=new Node();
		node.data=data;
		node.next=head;
		head=node;
		
	}
	
	public void insertAt(int pos,int data){
		Node node=new Node();
		node.data=data;
		
		if(pos==0)
		{
			insertAtstart(data);
		}
		
		else {
			Node n=head;
		for(int i=0;i<pos-1;i++){
			n=n.next;
			
		}
		node.next=n.next;
		n.next=node;
		}
	}
	
	public void delete(int pos){
		if(pos==0){
			head=head.next;
		}
		else
		{
			Node n=head;
			Node n1=null;
			for(int i=0;i<pos-1;i++){
				n=n.next;
				
			}
			n1=n.next;
			n.next=n1.next;
			System.out.println("n1"+n1.data);
		}
	}
	public void show()
	{
		Node n=head;
		while(n.next!=null)
		{
			System.out.println(n.data);
			n=n.next;
		}
		System.out.println(n.data);
	}
	
	

}
