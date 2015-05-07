M1 = csvread('data.csv',1,1);
M2 = csvread('data2.csv',1,1);
t1 = M1(:,2);
v1= M1(:,6);
t2 = M2(:,2);
v2= M2(:,6);
figure(2)
plot(t1,v1)
figure(3)
plot(t2,v2)