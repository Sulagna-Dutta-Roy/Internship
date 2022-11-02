# CRUD OPERATION WITH MYSQL AND MONGODB
# ===============================================================
#DB Creating
# =======================================================================

from mysql.connector import connect
con = connect(host="localhost",user="root",password="root")
cur = con.cursor()
cur.execute("Create Database EmployeeDB")
print("Successfully created db")
cur.execute("show databases")

for i in cur: 
    print(i)


# =============================================
# Table Creation

from mysql.connector import connect
con = connect(host="localhost",user="root",password="root",database="EmployeeDB")
cur = con.cursor()
cur.execute('''Create table EmployeeInfo(
            empId int,empName varchar(20),
            empage int,empSalary varchar(20),eexp int,empplace varchar(20))''')
            
print("Table creation is successful")
cur.execute("Show Tables")

for i in cur:
    print(i)
    
#============================
#Inserting values


from mysql.connector import connect
con = connect(host="localhost",user="root",password="root",database="EmployeeDB")
cur = con.cursor()
query = "insert into EmployeeInfo(empid,empname,empage,empsalary,eexp,empplace) values(%s,%s,%s,%s,%s,%s)"
records = [(100,"Sulagna",26,4000,1,"Kolkata"),(101,"Dutta",22,5000,2,"Hello"),(101,"Dutta",20,8000,3,"Hello")]
cur.executemany(query,records)
con.commit()
cur.execute("Select * from EmployeeInfo")
res=cur.fetchall()
for i in res:
    print(i)


# =======================================
# update

from mysql.connector import connect
con=connect(host="localhost", user="root", password="root", database="EmployeeDB")
cur=con.cursor()
cur.execute("update EmployeeInfo SET empsalary=40500 where empname='Hello,I am Sulagna'")
con.commit()
cur.execute("select * from EmployeeInfo")
res=cur.fetchall()

for i in res:
    print(i)

#==============================
#delete 

cur.execute("select * from EmployeeInfo")
res=cur.fetchall()
for i in res:
    print(i)

