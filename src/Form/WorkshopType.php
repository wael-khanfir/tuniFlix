<?php

namespace App\Form;

use App\Entity\Promotion;
use App\Entity\Workshop;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class WorkshopType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('Nomevent')
            ->add('lieu')
            ->add('nbparticipant')
            ->add('datedebut')
            ->add('datefin')
            ->add('type')
            ->add('description')
            ->add('prix')
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Workshop::class,
        ]);
    }
}
