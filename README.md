# Othello_Java
Javaの復習も兼ねてJavaで作成したオセロプログラム

## 更新履歴
2016/1/16 リファクタリング
2015/12/15 出来上がったものをコミット

## 開発環境
Eclipse

## 遊び方
1.プレイヤの登録

2.ゲーム開始

3.石を置くマスの入力（例：5c）

4.自分相手ともに石を置く場所がなくなったら終了

5.戦績表示

## CPUのアルゴリズム
### easy_computer
左上から順に探索し、最初に見つかった置ける場所に置く

よわい

## MiniMax_computer
ミニマックス法を用いたコンピュータ

レベルは読む手数のこと

レベル4以上から勝てなくなってくる
