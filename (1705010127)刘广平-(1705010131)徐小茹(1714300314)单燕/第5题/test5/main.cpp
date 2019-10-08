#include <stdio.h>
#include <iostream>
#include <ctime>
#include <algorithm>
using namespace std;
typedef long long ll;
const int cardnum = 52;
const int si = 3, inf = 1e7;
int a[si], b[si];

void showcard(int x) {
    int cc = (x - 1 ) / 13;
    int vv = (x - 1 ) % 13 + 1;

    if (cc == 0) printf(" ����");
    else if (cc == 1) printf(" ����");
    else if (cc == 2) printf(" ÷��");
    else printf(" ����");

    if (vv == 1) printf("A ");
    else if (vv == 11) printf("J ");
    else if (vv == 12) printf("Q ");
    else if (vv == 13) printf("K ");
    else printf("%d ", vv);
}
void generate() {
    //һ����ֻ��52�� �Ҳ����ظ� ��λ���� �ų��ظ� �ٶȺܿ�
    ll sta = 0;//intֻ��32λ װ����52���� Ҫ��ll
    srand( (unsigned)time( NULL));
    for (int i = 0; i < si; i++) {
        do {
            a[i] = rand() % cardnum + 1;
        } while (sta >> a[i] & 1);
        sta |= 1 << a[i];
    }
    for (int i = 0; i < si; i++) {
        do {
            b[i] = rand() % cardnum + 1;
        } while (sta >> b[i] & 1);
        sta |= 1 << b[i];
    }
    //����6���ƶ��ǲ��ظ���

    cout << endl << "a : "  ;
    for (int i = 0; i < si; i++) showcard(a[i]);
    cout <<"\n"<<endl;
    cout << endl << "b : "  ;
    for (int i = 0; i < si; i++) showcard(b[i]);
    cout << endl;
    cout <<"\n"<<endl;
}

void cal(int &g, int &v, int arr[]) {
    int color[si], val[si];//��ɫֵ
    int sum = 0;
    for (int i = 0; i < si; i++)  {
        color[i] = (arr[i] - 1 ) / 13; //��һ���Ƶı���� x ��(x - 1)/13�����Ļ�ɫ ���ֱ���0 1 2 3
        val[i] = (arr[i] - 1 ) % 13 + 1; //(x - 1) % 13  + 1������ֵ
        sum += val[i];
    }
    sort(val, val + si);//���򷽱�Ƚ�
    sort(color, color + si);



    int fl = 1;//ͬ��
    for (int i = 0; i < si - 1; i++)  if (color[i] != color[i + 1]) fl = 0;
    if (fl) {
        if (val[0] == 1 && val[1] == 12 && val[2] == 13) v = inf;
        else v = sum;
        g = 0;
        cout << " ͬ��\n" << endl;
        return ;
    }
    fl = 1;//˳�� ��Ϊǰ���Ѿ��ж��� ��ɫ�ǲ�ͬ�� ���Բ����ж���
    for (int i = 0; i < si - 1; i++)  if (val[i] + 1 != val[i + 1]) fl = 0;
    if (fl) {
        if (val[0] == 1 && val[1] == 12 && val[2] == 13) v = inf;
        else v = sum;
        g = 1;
        cout <<  " ˳��\n" << endl;
        return ;
    }
    fl = 1;//ͬ��
    for (int i = 0; i < si - 1; i++)  if (val[i] != val[i + 1]) fl = 0;
    if (fl) {
        if (sum == 3) v = inf;
        else v = sum;
        g = 2;
        cout << " ͬ��\n" << endl;
        return ;
    }
    fl = 0;//����
    for (int i = 0; i < si - 1; i++)  if (val[i] == val[i + 1]) fl = 1;
    if (fl) {
        if (val[0] == 1 && val[1] == 1 && val[2] == 13) v = inf;
        else v = sum;
        g = 3;
        cout << " ����\n" << endl;
        return ;
    }

    //����
    cout << " ����\n" << endl;
    if (val[0] == 1 && val[1] == 11 && val[2] == 13) v = inf;
    else v = sum;
    g = 4;
    return ;
}
int comparee() {//�Ƚ�
    int ag, av, bg, bv;// a�ȼ��͵��� b�ĵȼ��͵���
    cal(ag, av, a);//����a b�ĵȼ��͵��� 0����� ͬ�� ������4
    cal(bg, bv, b);

    printf("a���� : %d,   b���� : %d\n\n", av, bv);

    if (ag < bg)    return 1;
    if (ag > bg)    return -1;
    if (av > bv)    return 1;
    if (av < bv)    return -1;
    return 0;
}
int main() {
    printf("�밴�س�����ʼ����......");
    getchar();
    //�������б��� �����߼�ʵ��
    //����1 2 3...J Q K ��
    //1-13���� 14-26���� 27-39÷�� 40-52����
    // 0        1           2        3
    //��һ���Ƶı���� x ��(x - 1)/13�����Ļ�ɫ ���ֱ���0 1 2 3
    //(x - 1) % 13  + 1������ֵ
    //inf  ��ʾ���ֵȼ������ĵȼ�
    generate();//���������
    int s = comparee();//�Ƚ�

    if (s > 0) printf("aʤ�� \n");
    if (s < 0) printf("bʤ�� \n");
    if (s == 0) printf("ƽ�� \n");
    return 0;

}
