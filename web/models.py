from django.db import models

# Create your models here.
class User(models.Model):#继承模型类
    uname=models.CharField(max_length=10)#设置列名
    upwd=models.CharField(max_length=10)
    class Meta:#设置要存储的数据库名称,默认按照父类的名称创建
        db_table='users'
