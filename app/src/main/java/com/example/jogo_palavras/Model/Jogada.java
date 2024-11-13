package com.example.jogo_palavras.Model;

public class Jogada
{
   private int ordem;
   private int pontuacao;

   public Jogada(int ordem)
   {
      this.pontuacao = 30;
      this.ordem = ordem;
   }
   public void errou()
   {
      pontuacao -= 10;
   }

   public int getPontuacao() {
      return pontuacao;
   }

   public void setPontuacao(int pontuacao) {
      this.pontuacao = pontuacao;
   }


   public int getOrdem() {
      return ordem;
   }

   public void setOrdem(int ordem) {
      this.ordem = ordem;
   }

   public boolean isGanhou() {
      switch (this.pontuacao){
         case 30:
            return true;
         case 20:
            return false;
         case 10:
            return false;
         default:
            return false;
      }
   }
}
