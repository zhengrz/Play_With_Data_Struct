🚀🚀🚀🚀🚀🚀

### 一. 动态数组(Dynamic-Array)

> PS: 之所以采用均摊复杂度，是因为只有在某个情况才会触发resize方法扩容/缩容(复杂度O(n)), 所以使用均摊复杂度比较有意义

  1. java自带数组是静态数组，自己扩展的数组会自动扩容，即动态数组
  2. 动态数组采用均摊复杂度，即无论添加删除复杂度都是O(1)
  3. 数组扩容是立即扩容，缩容采用延迟缩容(lazy),防止复杂度震荡(情況： 添加与删除操作更换着调用，会出现复杂度震荡)


### 二. 动态栈(Dynamic-Stack)

  > 自动扩容数组栈，底层是动态数组

  > 利用自己创建栈解决一道与栈相关简单算法题, 再使用jdk自带栈解决，提高下自己吧 👍👍


### 三. 动态队列(Dynamic-Queue)

  1. 数组队列(Array-Queue)
     -  > 主要利用动态数组,遵循FIFO(所谓的先进先出), 队首队尾指针利用都是数组索引
  2. 循环队列(Loop-Queue)
     -  > 主要利用jdk中静态数组，并提供扩容方法，扩容临界在于队尾与队首指针是否指到同一个内存地址， 缩容在于元素数量(与动态数组一样)
     1. 队首指针(front)与队尾指针(tail)移动与计算
     2. 队首指针移动下一个位置计算公式:  (front + 1) % data.length
     3. 队尾指针移动下一个位置计算公式:  (tail + 1) % data.length
     4. 队列是否为空:   队首指针与队尾指针指的是否同一个内存地址, 即:  front == tail


### 四. 链表(LinkedList)

  > JDK的LinkedList本质是双向循环表,本LinkedList是简单的单向链表,后续有空补上实现


  1. 基本链表，锻炼内存指针，可以提高自己对内存的理解
  2. 里面实现链表有两种： 基本链表、（带虚拟头节点）的基本链表
  3. 里面有几种方法利用链表解决leetcode的算法问题，提高自己数据结构与算法解决能力


### 五. 二叉树(Binary-Tree)


### 六. 堆(Heap)

#### 1. 完全二叉最大堆(MaxHeap)

  ##### 性质:
  > 由于满足完全二叉树，因此复杂度基本上都是 O(log(n))
  > 父节点值必须大于左右子节点的值，但是左子节点不一定大于有右子节点，并且层数大小的节点大小没必然联系

  ##### 实现:
  > 使用数组存储二叉堆, 索引i初始化为0
    >-  parent(i) = (i - 1) / 2       // 从某个子节点找对应父节点索引
    >-  left child (i) = 2 * i + 1    // 从父节点找对应左节点索引
    >-  right child (i) = 2 * i + 2   // 从父节点找对应右节点索引
