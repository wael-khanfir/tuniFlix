<?php
namespace App\Services\Cart;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use App\Repository\ProduitRepository;

class CartService {
    protected $session;
    protected $produitRepository;
    public function __construct(SessionInterface $session, ProduitRepository $produitRepository){
        $this->session=$session;
        $this->produitRepository=$produitRepository;
    }
    public function add(int $id) {
        $panier=$this->session->get('panier', []);
        if(!empty($panier[$id])){
            $panier[$id]++;
        }else {
            $panier[$id]=1;
        }

        $this->session->set('panier', $panier);
    }
    public function remove(int $id) {
        $panier=$this->session->get('panier', []);
        if(!empty($panier[$id])){
            unset($panier[$id]);
        }

        $this->session->set('panier', $panier);
    }
    public function getFullCart() : array {
        $panier=$this->session->get('panier', []);
        $panierWithData=[];
        foreach($panier as $id => $quantity) {
            $panierWithData[]=[
                'produit'=>$this->produitRepository->find($id),
                'quantity'=>$quantity
            ];
        }
        return $panierWithData;
    }
    public function getTotal() : float {
        $total=0;

        foreach($this->getFullCart() as $item){
            $totalItem=
            $total+=$item['produit']->getPrix() * $item['quantity'];
        }
        return $total;
    }
}