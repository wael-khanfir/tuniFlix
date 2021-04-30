<?php

namespace App\Form;

use App\Entity\Projection;
use phpDocumentor\Reflection\Types\String_;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\FormTypeInterface;

class ProjectionType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom')
            ->add('genre', ChoiceType::class, [
                'choices'  => [
                    'comique'=> 'comique',
                    'horreur'=> 'horreur',
                    'action'=> 'action',
                    'aventure'=> 'aventure',
                    'science-fiction'=> 'science-fiction',
                    'mystere'=> 'myster',
                    'histoire'=> 'histoire'
                ],
            ])
            ->add('ageRecommande')
            ->add('duree')
            ->add('image',FileType::class, array('data_class' => null))
            ->add('description')
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Projection::class,
        ]);
    }
}
